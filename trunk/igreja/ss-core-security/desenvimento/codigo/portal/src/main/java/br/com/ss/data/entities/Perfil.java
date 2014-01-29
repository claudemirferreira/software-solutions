package br.com.ss.data.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Perfil
 * 
 */
@Entity
@Table(name = "saa_perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = -1550723647595201191L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "perf_nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "saa_usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
	private Set<Usuario> usuarios;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "saa_perfil_rotina", joinColumns = @JoinColumn(name = "rotina_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
	private Set<Rotina> rotinas;

	public Perfil() {
		super();
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Rotina> getRotinas() {
		return rotinas;
	}

	public void setRotinas(Set<Rotina> rotinas) {
		this.rotinas = rotinas;
	}

}