package br.com.cacau.vitrineapi.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cacau.vitrineapi.schedulers.NotificacaoDeHaProduto;
import lombok.Data;
import lombok.ToString;


@Entity

@Table(name = "produtos_estoque")
@Data
@ToString
public class ProdutoVitrine implements Serializable{

	private static final long serialVersionUID = 3200213257418572774L;

    private long id_vitrine = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_produto;
	
	private String nome_produto;
	
	private String tipo_produto;
	
	private String descricao_produto;
		
	private Double preco_produto;
	
	private Double quantidade_produto;
	
	private String unidade_medida;

    @Lob
    private byte[] foto_produto;

	public ProdutoVitrine( String nome_produto, String tipo_produto,
			String descricao_produto, Double preco_produto, Double quantidade_produto, String unidade_medida,
			byte[] foto_produto) {
		super();
		this.nome_produto = nome_produto;
		this.tipo_produto = tipo_produto;
		this.descricao_produto = descricao_produto;
		this.preco_produto = preco_produto;
		this.quantidade_produto = quantidade_produto;
		this.unidade_medida = unidade_medida;
		this.foto_produto = foto_produto;
	}

	public ProdutoVitrine() {
		super();
	}  
	
	@Autowired
	NotificacaoDeHaProduto notificacaoDeHaProduto;
	    
    @PostUpdate
    public void posUpdateProdutoVitrine() {
    	notificacaoDeHaProduto.avisarCliente();
		}	
}
