package br.com.ss.portal.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity implementation class for Entity: UsuarioPerfil
 * 
 */

@Entity
@Table(name = "saa_usuario_perfil", uniqueConstraints = @UniqueConstraint(columnNames = {
		"usuario_id", "perfil_id" }), catalog = "portal")
public class UsuarioPerfil extends AbstractEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = 5378741956614883953L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_perfil_id", unique = true, nullable = false)
	private Long idUsuarioPerfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil_id", nullable = false)
	private Perfil perfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@Column(name = "usu_perf_data", nullable = false)
	private Date data;

	public UsuarioPerfil() {
		super();
	}

	public Long getId() {
		return getIdUsuarioPerfil();
	}

	public Long getIdUsuarioPerfil() {
		return idUsuarioPerfil;
	}

	public void setIdUsuarioPerfil(Long idUsuarioPerfil) {
		this.idUsuarioPerfil = idUsuarioPerfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
