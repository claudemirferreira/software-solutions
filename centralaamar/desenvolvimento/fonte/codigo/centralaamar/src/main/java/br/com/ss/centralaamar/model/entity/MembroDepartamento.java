package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cent_membro_departamento", catalog = "portal")
public class MembroDepartamento extends AbstractEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = -3368137905470314734L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mem_depart_id", nullable = false)
	private Long idMembroDepartamento;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id", referencedColumnName = "mem_id")
	private Membro membro;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "carg_id", referencedColumnName = "carg_id")
	private Cargo cargo;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id", referencedColumnName = "depart_id")
	private Departamento departamento;

	@Override
	public Long getId() {
		return getIdMembroDepartamento();
	}

}
