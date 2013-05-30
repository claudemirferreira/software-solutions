package br.com.ss.portal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Rotina
 * 
 */
@Entity
@Table(name = "saa_rotina", catalog="portal")
public class Rotina extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 5078104430987992410L;

	@Id
	@Column(name = "rotina_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRotina;

	@Column(name = "rot_nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	@Column(name = "rot_status")
	private Boolean status;

	public Long getIdRotina() {
		return idRotina;
	}

	public void setIdRotina(Long idRotina) {
		this.idRotina = idRotina;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "rot_path", nullable = false, length = 60)
	private String path;

	public Rotina() {
		super();
	}

	@Override
	public Long getId() {
		return getIdRotina();
	}

}
