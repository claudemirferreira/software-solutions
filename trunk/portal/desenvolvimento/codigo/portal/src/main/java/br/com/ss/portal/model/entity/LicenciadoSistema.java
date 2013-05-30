package br.com.ss.portal.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "saa_licenciado_sistema", uniqueConstraints = @UniqueConstraint(columnNames = {
		"licenciado_id", "sistema_id" }), catalog = "portal")
public class LicenciadoSistema extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3917968713808885313L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "licenciado_sistema_id", nullable = false)
	private Long idLicenciadoSistema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "licenciado_id", nullable = false)
	private Licenciado licenciado;

	@Column(name = "lic_sis_numero", nullable = false, length = 30)
	private String numero;

	@Column(name = "lic_sis_tipo", nullable = false, length = 1)
	private String tipo;

	@Column(name = "lic_sis_validade")
	private Date validade;

	public LicenciadoSistema() {
	}

	public Long getId() {
		return getIdLicenciadoSistema();
	}

	public Long getIdLicenciadoSistema() {
		return idLicenciadoSistema;
	}

	public void setIdLicenciadoSistema(Long idLicenciadoSistema) {
		this.idLicenciadoSistema = idLicenciadoSistema;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Licenciado getLicenciado() {
		return licenciado;
	}

	public void setLicenciado(Licenciado licenciado) {
		this.licenciado = licenciado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

}
