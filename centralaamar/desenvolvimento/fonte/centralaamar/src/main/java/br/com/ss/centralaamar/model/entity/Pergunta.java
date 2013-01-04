package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pergunta", catalog = "centralaamar")
public class Pergunta implements java.io.Serializable {

	private static final long serialVersionUID = 845836539746268610L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "perg_id", unique = true, nullable = false)
	private Integer id;
	private String descricao;
	
	public Pergunta() {
	}

	public Pergunta(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "descricao", nullable = false, length = 100)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
