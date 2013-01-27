package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "grupo", catalog = "centralaamar")
public class Grupo implements Serializable {

	private static final long serialVersionUID = -4116112636792954532L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "grup_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(unique = true, nullable = false, length = 60)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany( fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Membro> membros = new ArrayList<Membro>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

}
