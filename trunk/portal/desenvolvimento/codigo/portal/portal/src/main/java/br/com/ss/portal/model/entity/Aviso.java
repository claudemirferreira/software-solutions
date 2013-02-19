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

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Aviso
 * 
 */
@Entity
@Table(name = "saa_aviso")
public class Aviso extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7539409961017046170L;

	@Getter
	@Setter
	@Id
	@Column(name = "aviso_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAviso;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "licenciado_sistema_id", nullable = false)
	private LicenciadoSistema licenciadoSistema;

	@Getter
	@Setter
	@Column(name = "avi_mensagem", nullable = false, length = 1000)
	private String mensagem;

	@Getter
	@Setter
	@Column(name = "avi_inicio", nullable = false)
	private Date inicio;

	@Getter
	@Setter
	@Column(name = "avi_fim", nullable = false)
	private Date fim;

	@Override
	public Long getId() {
		return getIdAviso();
	}

	public Aviso() {
		super();
	}

}
