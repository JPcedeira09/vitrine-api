package br.com.cacau.vitrineapi.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "vitrine")
@Data
@ToString
public class Vitrine implements Serializable{

	private static final long serialVersionUID = -5538990840545459713L;

	@Id
	private long id_vitrine;
	
	private String nome_vitrine;

	public Vitrine(long id_vitrine, String nome_vitrine) {
		super();
		this.id_vitrine = id_vitrine;
		this.nome_vitrine = nome_vitrine;
	}

}
