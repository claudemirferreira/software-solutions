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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saa_licenciado_sistema", uniqueConstraints = @UniqueConstraint(columnNames = {
		"licenciado_id", "sistema_id" }))
public class LicenciadoSistema extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3917968713808885313L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "licenciado_sistema_id", nullable = false)
	private Long idLicenciadoSistema;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "licenciado_id", nullable = false)
	private Licenciado licenciado;

	@Getter
	@Setter
	@Column(name = "lic_sis_numero", nullable = false, length = 30)
	private String numero;

	@Getter
	@Setter
	@Column(name = "lic_sis_tipo", nullable = false, length = 1)
	private String tipo;

	@Getter
	@Setter
	@Column(name = "lic_sis_validade")
	private Date validade;

	public LicenciadoSistema() {
	}

	public Long getId() {
		return getIdLicenciadoSistema();
	}

}
