package br.ss.authenticator.model.entity;
// Generated 09/05/2012 21:23:25 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema="authenticator", uniqueConstraints = @UniqueConstraint(columnNames = "tx_login"))
@SequenceGenerator( name="id_usuario_seq", sequenceName="authenticator.usuario_id_usuario_seq" )
public class Usuario implements java.io.Serializable {

	private Integer idUsuario;
	private String txLogin;
	private Date dtCadastro;
	private String txEmail;
	private String txSenha;
	private Short csStatus;
	private Set<UsuarioPerfil> usuarioPerfils = new HashSet<UsuarioPerfil>(0);

	public Usuario() {
	}

	public Usuario(Integer idUsuario, String txLogin, Date dtCadastro,
			String txSenha, Short csStatus) {
		this.idUsuario = idUsuario;
		this.txLogin = txLogin;
		this.dtCadastro = dtCadastro;
		this.txSenha = txSenha;
		this.csStatus = csStatus;
	}
	public Usuario(Integer idUsuario, String txLogin, Date dtCadastro,
			String txEmail, String txSenha, Short csStatus,
			Set<UsuarioPerfil> usuarioPerfils) {
		this.idUsuario = idUsuario;
		this.txLogin = txLogin;
		this.dtCadastro = dtCadastro;
		this.txEmail = txEmail;
		this.txSenha = txSenha;
		this.csStatus = csStatus;
		this.usuarioPerfils = usuarioPerfils;
	}

	@Id
	@GeneratedValue( generator="id_usuario_seq", strategy = GenerationType.SEQUENCE )
	@Column(name = "id_usuario", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "tx_login", unique = true, nullable = false, length = 11)
	@NotNull
	@Length(max = 11)
	public String getTxLogin() {
		return this.txLogin;
	}

	public void setTxLogin(String txLogin) {
		this.txLogin = txLogin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro", nullable = false, length = 29)
	@NotNull
	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	@Column(name = "tx_email", length = 60)
	@Length(max = 60)
	public String getTxEmail() {
		return this.txEmail;
	}

	public void setTxEmail(String txEmail) {
		this.txEmail = txEmail;
	}

	@Column(name = "tx_senha", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getTxSenha() {
		return this.txSenha;
	}

	public void setTxSenha(String txSenha) {
		this.txSenha = txSenha;
	}

	@Column(name = "cs_status", nullable = false)
	public Short getCsStatus() {
		return this.csStatus;
	}

	public void setCsStatus(Short csStatus) {
		this.csStatus = csStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<UsuarioPerfil> getUsuarioPerfils() {
		return this.usuarioPerfils;
	}

	public void setUsuarioPerfils(Set<UsuarioPerfil> usuarioPerfils) {
		this.usuarioPerfils = usuarioPerfils;
	}

}
