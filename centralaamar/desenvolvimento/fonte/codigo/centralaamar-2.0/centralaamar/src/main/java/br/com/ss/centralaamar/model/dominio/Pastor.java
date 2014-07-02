package br.com.ss.centralaamar.model.dominio;

// Generated 02/12/2012 11:09:42 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Profissao generated by hbm2java
 */
@Entity
@Table(name = "cent_pastor")
public class Pastor extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 845836539746268610L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "past_id", nullable = false)
	private Long idPastor;

	@Column(unique = true, nullable = false, length = 100)
	private String nome;

	public Pastor() {
	}

	public Pastor(String nome) {
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return getIdPastor();
	}

	public Long getIdPastor() {
		return idPastor;
	}

	public void setIdPastor(Long idPastor) {
		this.idPastor = idPastor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}