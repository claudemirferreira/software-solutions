package br.com.ss.portal.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Perfil
 * 
 */
@Entity
@Table(name = "saa_perfil", catalog = "portal")
public class Perfil extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -1550723647595201191L;

	@Id
	@Column(name = "perfil_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfil;

	@Column(name = "perf_nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	public Perfil() {
		super();
	}

	@Override
	public Long getId() {
		return getIdPerfil();
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

}
