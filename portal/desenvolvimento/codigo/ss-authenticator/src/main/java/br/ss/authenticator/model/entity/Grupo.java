package br.ss.authenticator.model.entity;

// Generated 11/07/2012 15:35:00 by Hibernate Tools 3.4.0.CR1

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
import javax.persistence.UniqueConstraint;

import br.ss.core.model.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Grupo generated by hbm2java
 */
@Entity
@Table(name = "grupo", schema = "authenticator", uniqueConstraints = @UniqueConstraint(columnNames = "tx_grupo"))
@SequenceGenerator(name = "grupo_id_grupo_seq", sequenceName = "authenticator.grupo_id_grupo_seq")
public class Grupo extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -6108904704851702523L;
	
	@Getter  @Setter
	@GeneratedValue(generator = "grupo_id_grupo_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_grupo", unique = true, nullable = false)
	private Integer idGrupo;

	@Getter  @Setter
	@Column(name = "tx_grupo", nullable = false, length = 80)
	private String txGrupo;

	@Getter  @Setter
	@Column(name = "cs_ativo", nullable = false)
	private Boolean csAtivo;

	@Getter  @Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	private Set<GrupoUsuario> grupoUsuarios = new HashSet<GrupoUsuario>(0);

	public Grupo() { }

	public Grupo(Integer idGrupo, String txGrupo, boolean csAtivo) {
		this.idGrupo = idGrupo;
		this.txGrupo = txGrupo;
		this.csAtivo = csAtivo;
	}

	public Grupo(Integer idGrupo, String txGrupo, boolean csAtivo,
			Set<GrupoUsuario> grupoUsuarios) {
		this.idGrupo = idGrupo;
		this.txGrupo = txGrupo;
		this.csAtivo = csAtivo;
		this.grupoUsuarios = grupoUsuarios;
	}

}