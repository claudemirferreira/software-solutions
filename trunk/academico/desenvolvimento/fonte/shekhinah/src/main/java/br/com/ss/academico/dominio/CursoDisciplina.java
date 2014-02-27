package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient; 

/**
 * 
 * @author altitdb
 */
@Entity
@AssociationOverrides({
		@AssociationOverride(name = "cursoDisciplinaPk.curso", joinColumns = @JoinColumn(name = "idCurso")),
		@AssociationOverride(name = "cursoDisciplinaPk.disciplina", joinColumns = @JoinColumn(name = "idDisciplina")) })
@Table( name = "acad_curso_disciplina" )
public class CursoDisciplina implements Serializable {

	private static final long serialVersionUID = -1220797610390530939L;

	@EmbeddedId
	private CursoDisciplinaPk cursoDisciplinaPk = new CursoDisciplinaPk();

	@Transient
	private Curso curso;

	@Transient
	private Disciplina disciplina;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	
	public Disciplina getDisciplina() {
		return cursoDisciplinaPk.getDisciplina();
	}

	public void setDisciplina(Disciplina disciplina) {
		cursoDisciplinaPk.setDisciplina(disciplina);
	}

	public Curso getCurso() {
		return cursoDisciplinaPk.getCurso();
	}

	public void setCurso(Curso curso) {
		cursoDisciplinaPk.setCurso(curso);
	}

	public CursoDisciplinaPk getCursoDisciplinaPk() {
		return cursoDisciplinaPk;
	}

	public void setCursoDisciplinaPk(CursoDisciplinaPk cursoDisciplinaPk) {
		this.cursoDisciplinaPk = cursoDisciplinaPk;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		return cursoDisciplinaPk.equals( ( ( CursoDisciplina ) obj ).getCursoDisciplinaPk() );
	}

	@Override
	public int hashCode() {
		return cursoDisciplinaPk.hashCode();
	}
	
}