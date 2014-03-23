package br.com.ss.centralaamar.model.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cent_sabado")
public class Sabado extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -8386852683843818049L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "saba_id", nullable = false)
	private Long idSabado;

	@Column(name = "data", unique = true, nullable = false)
	private Date data;

	public Long getId() {
		return getIdSabado();
	}

	public Long getIdSabado() {
		return idSabado;
	}

	public void setIdSabado(Long idSabado) {
		this.idSabado = idSabado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
