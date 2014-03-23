package br.com.ss.centralaamar.model.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cent_membro_departamento", catalog = "portal")
public class MembroDepartamento extends AbstractEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = -3368137905470314734L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mem_depart_id", nullable = false)
	private Long idMembroDepartamento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id", referencedColumnName = "mem_id")
	private Membro membro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "carg_id", referencedColumnName = "carg_id")
	private Cargo cargo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id", referencedColumnName = "depart_id")
	private Departamento departamento;

	@Override
	public Long getId() {
		return getIdMembroDepartamento();
	}

	public Long getIdMembroDepartamento() {
		return idMembroDepartamento;
	}

	public void setIdMembroDepartamento(Long idMembroDepartamento) {
		this.idMembroDepartamento = idMembroDepartamento;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}