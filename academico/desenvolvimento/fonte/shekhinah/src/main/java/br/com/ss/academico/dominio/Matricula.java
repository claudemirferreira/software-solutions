package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.ss.academico.enumerated.StatusMatricula;

/**
 * The persistent class for the iansa_curso database table.
 */

@Entity
@Table(name = "acad_matricula")
public class Matricula extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 8382566065711875093L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idMatricula;

	private Date data;

	private double valor;

	@Column(length = 1, nullable = false)
	private StatusMatricula status;
	
	private boolean integral; 

	@ManyToOne
	@JoinColumn(name = "id_turma", nullable = false)
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "id_aluno", nullable = false)
	private Aluno aluno;

	@OneToMany(mappedBy = "matricula")
	private List<Mensalidade> mensalidades = new ArrayList<Mensalidade>();
	
	public Matricula() {
	}

	@Override
	public Long getId() {
		return this.idMatricula;
	}

	public Long getIdMatricula() {
		return this.idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public StatusMatricula getStatus() {
		return status;
	}

	public void setStatus(StatusMatricula status) {
		this.status = status;
	}

	public boolean isIntegral() {
		return integral;
	}

	public void setIntegral(boolean integral) {
		this.integral = integral;
	}

	public List<Mensalidade> getMensalidades() {
		return mensalidades;
	}

	public void setMensalidades(List<Mensalidade> mensalidades) {
		this.mensalidades = mensalidades;
	}

}