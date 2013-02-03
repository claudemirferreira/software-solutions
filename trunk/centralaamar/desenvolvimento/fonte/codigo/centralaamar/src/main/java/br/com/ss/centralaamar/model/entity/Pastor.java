package br.com.ss.centralaamar.model.entity;

// Generated 02/12/2012 11:09:42 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Profissao generated by hbm2java
 */
@Entity
@Table(name = "pastor", catalog = "centralaamar")
public class Pastor extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 845836539746268610L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "past_id", nullable = false)
	private Long idPastor;

	@Getter @Setter
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

}
