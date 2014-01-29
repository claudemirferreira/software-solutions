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
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Usuario
 * 
 */
@Entity
@Table(name = "saa_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 8626411055461539204L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "usua_nome", nullable = false, length = 60)
	private String nome;

	// @Column(name = "usua_login", nullable = false, length = 11)
	// private String login;

	@Column(name = "usua_cpf", nullable = false, length = 11)
	private String cpf;

	@Column(name = "usua_senha", nullable = true, length = 100)
	private String senha;

	private String status;

	@Column(name = "usua_email", nullable = true, length = 60)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "saa_usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
	private Set<Perfil> perfis;

	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

}