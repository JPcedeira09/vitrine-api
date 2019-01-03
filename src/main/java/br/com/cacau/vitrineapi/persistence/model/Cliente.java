package br.com.cacau.vitrineapi.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "cliente_prefeido")
@Data
@ToString
public class Cliente {

	private String nome; 
	private String email; 
	private String telefone; 
	private String celular;
	private long id_produto_vitrine;
		
}
