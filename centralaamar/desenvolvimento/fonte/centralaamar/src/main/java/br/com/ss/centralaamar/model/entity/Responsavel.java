package br.com.ss.centralaamar.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Responsavel", catalog = "centralaamar")
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 7266736354128274117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id")
	private int id;

	@Column(nullable = false, length = 60)
	@NotNull()
	private String nome;

//	@OneToMany(mappedBy = "responsavel", fetch = FetchType.EAGER)
//	@Cascade(CascadeType.PERSIST)
	@OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.SAVE_UPDATE)
	private List<Eleitor> eleitores = new ArrayList<Eleitor>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Eleitor> getEleitores() {
		return eleitores;
	} 

	public void setEleitores(List<Eleitor> eleitores) {
		this.eleitores = eleitores;
	}

	public String toString() {
		return " id = " + getId() + "\n nome = " + getNome();
	}

	public Responsavel toUpperCase(Responsavel responsavel) {
		responsavel.setNome(responsavel.getNome().toUpperCase());
		return responsavel;
	}
	
}
