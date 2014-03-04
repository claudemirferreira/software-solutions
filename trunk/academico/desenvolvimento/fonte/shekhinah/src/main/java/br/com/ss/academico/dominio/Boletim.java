package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "acad_boletim")
public class Boletim extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -6438912749527248323L;
	
	/*
	 * TODO validar mapeamento - acho que deve criar uma tabela para lançar 
	 * as notas 
	 * */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBoletim;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "turmaDisciplinaPk.disciplina")
	private List<TurmaDisciplina> turmaDisciplina = new ArrayList<TurmaDisciplina>();
	
	
	public Boletim() { }
	
	@Override
	public Long getId() {
		return idBoletim; 
	}

	public Long getIdBoletim() {
		return idBoletim;
	}

	public void setIdBoletim(Long idBoletim) {
		this.idBoletim = idBoletim;
	}

	public List<TurmaDisciplina> getTurmaDisciplina() {
		return turmaDisciplina;
	}

	public void setTurmaDisciplina(List<TurmaDisciplina> turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}
	
}
