package br.com.ss.centralaamar.model.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author altitdb
 */
@Entity
@Table(name = "saa_usuario")
public class Usuario extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7789936704890560797L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUsuario;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "usuarioPerfilPk.usuario")
	private List<UsuarioPerfil> usuarioPerfil = new ArrayList<UsuarioPerfil>();

	@Column(nullable = false, length = 30)
	private String nome;

	@Column(unique = true, length = 30)
	private String login;

	private String senha;

	@Column(name = "status", length = 1, columnDefinition = "CHAR(1)", nullable = false)
	private String status;

	@Override
	public Long getId() {
		return idUsuario;
	}

	
	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario() {
	}

	@Transient
	public UsuarioPerfil getUsuarioPerfilByPerfil(Perfil perfil) {
		for (UsuarioPerfil up : usuarioPerfil) {
			if (up.getUsuarioPerfilPk().getPerfil().equals(perfil)) {
				return up;
			}
		}
		return null;
	}

	@Transient
	public boolean containsPerfil(Perfil perfil) {
		return getUsuarioPerfilByPerfil(perfil) != null;
	}

	public List<UsuarioPerfil> getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(List<UsuarioPerfil> usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}