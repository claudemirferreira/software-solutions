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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Local", catalog = "centralaamar")
public class Local implements Serializable {

	private static final long serialVersionUID = 880258715184615348L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loc_id")
	private int id;

	@Column(name = "nome", length = 60, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "local", fetch = FetchType.LAZY)
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

}
