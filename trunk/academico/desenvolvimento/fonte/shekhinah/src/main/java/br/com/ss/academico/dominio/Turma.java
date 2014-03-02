package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.ss.academico.enumerated.Turno;

/**
 * The persistent class for the iansa_turma database table.
 * 
 */
@Entity
@Table(name = "acad_turma")
public class Turma extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -5155558399272953990L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTurma;

	@Column(nullable = false, length = 1)
	private Turno turno;

	@Column(nullable = false, length = 4)
	private Integer ano;

	@Column(nullable = false)
	private Integer numeroVagas;

	// //bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy = "turma")
	private List<Matricula> matriculas;

	// bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name = "id_curso", nullable = false)
	private Curso curso;

	public Turma() {
	}

	@Override
	public Long getId() {
		return this.idTurma;
	}

	public Long getIdTurma() {
		return this.idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(Integer numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

}