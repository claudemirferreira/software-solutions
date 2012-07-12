package br.ss.authenticator.model.entity;

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
import br.ss.core.constant.UsuarioStatusConstant;
import br.ss.core.model.entity.AbstractEntity;

@Entity
@Table(name = "usuario_status", schema = "authenticator")
@SequenceGenerator(name = "usuario_status_id_usuario_status_seq", sequenceName = "authenticator.usuario_status_id_usuario_status_seq")
public class UsuarioStatus extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -598719617462955147L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(generator = "usuario_status_id_usuario_status_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_usuario", unique = true, nullable = false)
	private Long idUsuario;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false, insertable=false, updatable=false )
	private Usuario usuario;

	@Getter @Setter
    @Column(name="tx_justificativa", nullable=false, length=255)
    private String txJustificativa;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
    @Column( name = "dt_status", nullable = false, length = 8 )
    /** data de atualização. */
    private Date dtStatus;
    
	@Getter
	@Setter
	@Column(name = "cs_status", nullable = false)
	@Enumerated
	private UsuarioStatusConstant csStatus;

	public UsuarioStatus() {
	}

}
