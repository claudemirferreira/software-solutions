package br.ss.authenticator.model.entity;

// Generated 11/07/2012 15:35:00 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

import br.ss.core.constant.CsTerminoConstant;
import br.ss.core.model.entity.AbstractEntity;

/**
 * UsuarioPerfil generated by hbm2java
 */
@Entity
@Table(name = "usuario_perfil", schema = "authenticator", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_usuario", "id_perfil" }))
@SequenceGenerator(name = "usuario_perfil_id_usuario_perfil_seq", sequenceName = "authenticator.usuario_perfil_id_usuario_perfil_seq")
public class UsuarioPerfil extends AbstractEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 2870528211399653473L;
	
	@Getter @Setter
	@GeneratedValue(generator = "usuario_perfil_id_usuario_perfil_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_usuario_perfil", unique = true, nullable = false)
	private Integer idUsuarioPerfil;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_designador", nullable = false)
	private Usuario usuarioDesignador;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil", nullable = false)
	private Perfil perfil;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio", nullable = false, length = 22)
	private Date dtInicio;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_fim", length = 22)
	private Date dtFim;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao", length = 29)
	private Date dtCriacao;

	@Getter @Setter
	@Column(name = "cs_termino", nullable = false)
	@Enumerated
	private CsTerminoConstant csTermino;

	@Getter @Setter
	@Column(name = "cs_excluido", nullable = false)
	private Boolean csExcluido;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_atribuicao", nullable = false, length = 29)
	private Date dtAtribuicao;

	@Getter @Setter
	@Column(name = "tx_motivo")
	private String txMotivo;

	@Getter @Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioPerfil")
	private Set<UsuarioAcesso> usuarioAcessos = new HashSet<UsuarioAcesso>(0);

	
	public UsuarioPerfil() { }

	public UsuarioPerfil(int idUsuarioPerfil,
			Usuario usuarioByIdUsuarioDesignador, Usuario usuarioByIdUsuario,
			Perfil perfil, Date dtInicio, CsTerminoConstant csTermino, Boolean csExcluido,
			Date dtAtribuicao) {
		this.idUsuarioPerfil = idUsuarioPerfil;
		this.usuarioDesignador = usuarioByIdUsuarioDesignador;
		this.usuario = usuarioByIdUsuario;
		this.perfil = perfil;
		this.dtInicio = dtInicio;
		this.csTermino = csTermino;
		this.csExcluido = csExcluido;
		this.dtAtribuicao = dtAtribuicao;
	}

	public UsuarioPerfil(int idUsuarioPerfil,
			Usuario usuarioByIdUsuarioDesignador, Usuario usuarioByIdUsuario,
			Perfil perfil, Date dtInicio, Date dtFim, Date dtCriacao,
			CsTerminoConstant csTermino, Boolean csExcluido, Date dtAtribuicao,
			String txMotivo, Set<UsuarioAcesso> usuarioAcessos) {
		this.idUsuarioPerfil = idUsuarioPerfil;
		this.usuarioDesignador = usuarioByIdUsuarioDesignador;
		this.usuario = usuarioByIdUsuario;
		this.perfil = perfil;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.dtCriacao = dtCriacao;
		this.csTermino = csTermino;
		this.csExcluido = csExcluido;
		this.dtAtribuicao = dtAtribuicao;
		this.txMotivo = txMotivo;
		this.usuarioAcessos = usuarioAcessos;
	}

}
