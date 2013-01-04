package br.ss.authenticator.model.entity;

// Generated 11/07/2012 15:35:00 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import br.ss.core.constant.CsTerminoConstant;
import br.ss.core.model.entity.AbstractEntity;

/**
 * GrupoUsuario generated by hbm2java
 */
@Entity
@Table(name = "grupo_usuario", schema = "authenticator")
@SequenceGenerator(name = "grupo_usuario_id_grupo_usuario_seq", sequenceName = "authenticator.grupo_usuario_id_grupo_usuario_seq")
public class GrupoUsuario extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2583233340198487875L;
	
	@Getter @Setter
	@GeneratedValue(generator = "grupo_usuario_id_grupo_usuario_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_grupo_usuario", unique = true, nullable = false)
	private Integer idGrupoUsuario;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_designador", nullable = false)
	private Usuario usuarioDesignador;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo", nullable = false)
	private Grupo grupo;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio", nullable = false, length = 22)
	private Date dtInicio;

	@Getter @Setter
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_fim", length = 13)
	private Date dtFim;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_atribuicao", nullable = false, length = 22)
	private Date dtAtribuicao;

	@Getter @Setter
	@Enumerated
	@Column(name = "cs_termino", nullable = false)
	private CsTerminoConstant csTermino;

	@Getter @Setter
	@Column(name = "cs_excluido", nullable = false)
	private Boolean csExcluido;

	@Getter @Setter
	@Column(name = "tx_motivo")
	private String txMotivo;

	public GrupoUsuario() {
	}

	public GrupoUsuario(Integer idGrupoUsuario,
			Usuario usuarioByIdUsuarioDesignador, Usuario usuarioByIdUsuario,
			Grupo grupo, Date dtInicio, Date dtAtribuicao, CsTerminoConstant csTermino,
			boolean csExcluido) {
		this.idGrupoUsuario = idGrupoUsuario;
		this.usuarioDesignador = usuarioByIdUsuarioDesignador;
		this.usuario = usuarioByIdUsuario;
		this.grupo = grupo;
		this.dtInicio = dtInicio;
		this.dtAtribuicao = dtAtribuicao;
		this.csTermino = csTermino;
		this.csExcluido = csExcluido;
	}

	public GrupoUsuario(Integer idGrupoUsuario,
			Usuario usuarioByIdUsuarioDesignador, Usuario usuarioByIdUsuario,
			Grupo grupo, Date dtInicio, Date dtFim, Date dtAtribuicao,
			CsTerminoConstant csTermino, boolean csExcluido, String txMotivo) {
		this.idGrupoUsuario = idGrupoUsuario;
		this.usuarioDesignador = usuarioByIdUsuarioDesignador;
		this.usuario = usuarioByIdUsuario;
		this.grupo = grupo;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.dtAtribuicao = dtAtribuicao;
		this.csTermino = csTermino;
		this.csExcluido = csExcluido;
		this.txMotivo = txMotivo;
	}

}