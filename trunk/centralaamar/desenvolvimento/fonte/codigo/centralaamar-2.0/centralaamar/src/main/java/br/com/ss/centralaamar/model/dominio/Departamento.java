package br.com.ss.centralaamar.model.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cent_departamento", catalog = "portal")
public class Departamento extends AbstractEntity implements
		java.io.Serializable {
	private static final long serialVersionUID = -4116112636792954532L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "depart_id", nullable = false)
	private Long idDepartamento;

	@Column(unique = true, nullable = false, length = 60)
	private String nome;

	@Override
	public Long getId() {
		return getIdDepartamento();
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}