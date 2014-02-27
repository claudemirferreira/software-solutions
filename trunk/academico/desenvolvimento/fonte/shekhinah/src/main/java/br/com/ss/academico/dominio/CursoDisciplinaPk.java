package br.com.ss.academico.dominio;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author altitdb
 */
@Embeddable
public class CursoDisciplinaPk implements Serializable {

	private static final long serialVersionUID = -8682045998279798805L;

	@ManyToOne
	@JoinColumn(name = "idCurso")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "idDisciplina")
	private Disciplina disciplina;

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		if (!(obj instanceof CursoDisciplinaPk)) {
			return false;
		}

		final CursoDisciplinaPk other = (CursoDisciplinaPk) obj;
		if (this.curso != other.curso
				&& (this.curso == null || !this.curso.equals(other.curso))) {
			return false;
		}
		if (this.disciplina != other.disciplina
				&& (this.disciplina == null || !this.disciplina
						.equals(other.disciplina))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + (this.curso != null ? this.curso.hashCode() : 0);
		hash = 97 * hash
				+ (this.disciplina != null ? this.disciplina.hashCode() : 0);
		return hash;
	}

}
