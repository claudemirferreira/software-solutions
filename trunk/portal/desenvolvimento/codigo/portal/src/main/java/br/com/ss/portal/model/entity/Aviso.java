package br.com.ss.portal.model.entity;

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

/**
 * Entity implementation class for Entity: Aviso
 * 
 */
@Entity
@Table(name = "saa_aviso", catalog = "portal")
public class Aviso extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7539409961017046170L;

	@Id
	@Column(name = "aviso_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAviso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "licenciado_sistema_id", nullable = false)
	private LicenciadoSistema licenciadoSistema;

	@Column(name = "avi_mensagem", nullable = false, length = 1000)
	private String mensagem;

	@Column(name = "avi_inicio", nullable = false)
	private Date inicio;

	@Column(name = "avi_fim", nullable = false)
	private Date fim;

	@Override
	public Long getId() {
		return getIdAviso();
	}

	public Aviso() {
		super();
	}

	public Long getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(Long idAviso) {
		this.idAviso = idAviso;
	}

	public LicenciadoSistema getLicenciadoSistema() {
		return licenciadoSistema;
	}

	public void setLicenciadoSistema(LicenciadoSistema licenciadoSistema) {
		this.licenciadoSistema = licenciadoSistema;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
