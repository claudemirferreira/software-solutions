package br.com.ss.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Sistema
 * 
 */
@Entity
@Table(name = "saa_sistema")
public class Sistema implements Serializable {

	private static final long serialVersionUID = 168661018940283398L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "sist_nome", nullable = false, length = 60)
	private String nome;

	@Column(name = "sist_descricao", nullable = true, length = 100)
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Sistema() {
		super();
	}

}