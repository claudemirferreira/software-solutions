package br.com.ss.portal.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Licenciado
 * 
 */
@Entity
@Table(name = "saa_licenciado", catalog="portal")
public class Licenciado extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 138084726069636595L;

	@Getter
	@Setter
	@Id
	@Column(name = "licenciado_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLicenciado;

	@Getter
	@Setter
	@Column(name = "lic_nome", nullable = false, length = 60)
	private String nome;

	@Getter
	@Setter
	@Column(name = "lic_cpfcnpj", nullable = false, length = 14)
	private String cpfCnpj;

	public Licenciado() {
		super();
	}

	@Override
	public Long getId() {
		return getIdLicenciado();
	}

}
