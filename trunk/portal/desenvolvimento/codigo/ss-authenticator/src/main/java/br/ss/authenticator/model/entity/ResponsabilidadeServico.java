package br.ss.authenticator.model.entity;

// Generated 11/07/2012 15:35:00 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import lombok.Getter;
import lombok.Setter;
import br.ss.core.model.entity.AbstractEntity;

/**
 * ResponsabilidadeServico generated by hbm2java
 */
@Entity
@Table(name = "responsabilidade_servico", schema = "authenticator")
@SequenceGenerator(name = "responsabilidade_servico_id_responsabilidade_servico_seq", sequenceName = "authenticator.responsabilidade_servico_id_responsabilidade_servico_seq")
public class ResponsabilidadeServico extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -5030951883568068968L;
	
	@Getter @Setter
	@Id
	@GeneratedValue( generator = "responsabilidade_servico_id_responsabilidade_servico_seq", strategy = GenerationType.SEQUENCE )
	@Column(name = "id_responsabilidade_servico", unique = true, nullable = false)
	private Integer idResponsabilidadeServico;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_responsabilidade", nullable = false)
	private Responsabilidade responsabilidade;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_servico", nullable = false)
	private Servico servico;
	
	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio", nullable = false, length = 29)
	private Date dtInicio;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_fim", length = 29)
	private Date dtFim;

	@Getter @Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "responsabilidadeServico")
	private Set<UsuarioAcesso> usuarioAcessos = new HashSet<UsuarioAcesso>(0);

	public ResponsabilidadeServico() { }

	public ResponsabilidadeServico(Integer idResponsabilidadeServico, 
									Responsabilidade responsabilidade, 
									Servico servico,
									Date dtInicio, 
									Date dtFim,
									Set<UsuarioAcesso> usuarioAcessos) {
		this.idResponsabilidadeServico = idResponsabilidadeServico;
		this.responsabilidade = responsabilidade;
		this.servico = servico;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.usuarioAcessos = usuarioAcessos;
	}

}