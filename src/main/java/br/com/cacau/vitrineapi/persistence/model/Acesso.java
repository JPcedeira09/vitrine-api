package br.com.cacau.vitrineapi.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "acesso_motorize")
@Data
@ToString
public class Acesso implements Serializable{

	private static final long serialVersionUID = -5155771369836361744L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_acesso;
	
	private String email;
	
	private String usuario;
	
	private String senha;
	
    @Column(nullable = false)   
	private Boolean logavel = false;
}
