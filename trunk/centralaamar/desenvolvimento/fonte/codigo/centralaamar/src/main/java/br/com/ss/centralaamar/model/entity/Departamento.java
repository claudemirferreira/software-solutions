package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cent_departamento", catalog = "portal")
public class Departamento extends AbstractEntity implements
		java.io.Serializable {
	private static final long serialVersionUID = -4116112636792954532L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "depart_id", nullable = false)
	private Long idDepartamento;

	@Getter
	@Setter
	@Column(unique = true, nullable = false, length = 60)
	private String nome;

	@Override
	public Long getId() {
		return getIdDepartamento();
	}
}
