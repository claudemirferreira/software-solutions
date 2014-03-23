package br.com.ss.centralaamar.model.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cent_cargo", catalog = "portal")
public class Cargo extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -4116112636792954532L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "carg_id", nullable = false)
	private Long idCargo;

	@Column(unique = true, nullable = false, length = 60)
	private String nome;

	@Override
	public Long getId() {
		return getIdCargo();
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
