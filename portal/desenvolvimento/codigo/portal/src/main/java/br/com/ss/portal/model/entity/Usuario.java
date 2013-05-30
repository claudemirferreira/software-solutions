package br.com.ss.portal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Usuario
 * 
 */
@Entity
@Table(name = "saa_usuario", catalog="portal")
public class Usuario extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 8626411055461539204L;

	@Id
	@Column(name = "usuario_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "usu_nome", nullable = false, length = 60)
	private String nome;

	@Column(name = "usu_cpf", nullable = false, length = 11, unique = true)
	private String cpf;

	@Column(name = "usu_senha", nullable = true, length = 100)
	private String senha;

	@Column(name = "usu_status", nullable = false, length = 1)
	private String status;

	@Column(name = "usu_email", nullable = true, length = 60)
	private String email;

	public Usuario() {
		super();
	}

	public Long getId() {
		return getIdUsuario();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
