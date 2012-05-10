package br.ss.authenticator.model.entity;
// Generated 09/05/2012 21:23:25 by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsuarioPerfilId generated by hbm2java
 */
@Embeddable
public class UsuarioPerfilId implements java.io.Serializable {

	private Integer idUsuario;
	private Integer idPerfil;

	public UsuarioPerfilId() {
	}

	public UsuarioPerfilId(Integer idUsuario, Integer idPerfil) {
		this.idUsuario = idUsuario;
		this.idPerfil = idPerfil;
	}

	@Column(name = "id_usuario", nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "id_perfil", nullable = false)
	public Integer getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsuarioPerfilId))
			return false;
		UsuarioPerfilId castOther = (UsuarioPerfilId) other;

		return (this.getIdUsuario() == castOther.getIdUsuario())
				&& (this.getIdPerfil() == castOther.getIdPerfil());
	}

	public int hashCode() {
		Integer result = 17;

		result = 37 * result + this.getIdUsuario();
		result = 37 * result + this.getIdPerfil();
		return result;
	}

}
