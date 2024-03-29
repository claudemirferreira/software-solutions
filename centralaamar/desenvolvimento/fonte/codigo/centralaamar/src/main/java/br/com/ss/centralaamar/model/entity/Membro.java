package br.com.ss.centralaamar.model.entity;

// Generated 02/12/2012 11:09:42 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

/**
 * Membro generated by hbm2java
 */
@Entity
@Table(name = "cent_membro")
public class Membro extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -9176551890483396580L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mem_id", unique = true, nullable = false)
	private Long idMembro;

	@Getter
	@Setter
	@Column(name = "nome", nullable = false, length = 100, unique = true)
	private String nome;

	@Getter
	@Setter
	@Column(name = "endereco", length = 200)
	private String endereco;

	@Getter
	@Setter
	@Column(name = "bairro", length = 30)
	private String bairro;

	@Getter
	@Setter
	@Column(name = "celular", length = 12)
	private String celular;

	@Getter
	@Setter
	@Column(name = "fone_residencial", length = 12)
	private String foneResidencial;

	@Getter
	@Setter
	@Column(name = "fone_comercial", length = 12)
	private String foneComercial;

	@Getter
	@Setter
	// @Enumerated
	@Column(name = "sexo", nullable = false)
	// private MasculinoFemininoDomain sexo;e
	private String sexo;

	@Getter
	@Setter
	// @Enumerated
	@Column(name = "membro_igreja", nullable = false)
	// private SimNaoDomain membroIgreja;
	private String membroIgreja;

	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Getter
	@Setter
	@Column(name = "data_batismo")
	@Temporal(TemporalType.DATE)
	private Date dataBatismo;

	@Getter
	@Setter
	@Column(name = "email", length = 60)
	@Email(message = "E-mail inv�lido!")
	private String email;

	@Getter
	@Setter
	// @Enumerated
	@Column(name = "batizado")
	// private BatizadoDomain batizado;
	private String batizado;

	@Getter
	@Setter
	// @Enumerated
	@Column(name = "tem_filho")
	// private SimNaoDomain temFilho;
	private String temFilho;

	@Getter
	@Setter
	// @Enumerated
	@Column(name = "modo_conversao")
	// private ModoConversaoDomain modoConversao;
	private String modoConversao;

	@Getter
	@Setter
	@Column(name = "membros_batizado_familia", length = 255)
	private String membrosBatizadoFamilia;

	@Getter
	@Setter
	@Column(name = "pai", length = 100)
	private String pai;

	@Getter
	@Setter
	@Column(name = "mae", length = 100)
	private String mae;

	@Getter
	@Setter
	@Column(name = "amigo_contato", length = 100)
	private String amigoContato;

	@Getter
	@Setter
	@Column(name = "interesse", length = 50)
	private String interesse;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
	private Profissao profissao;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peq_id", referencedColumnName = "peq_id")
	private PequenoGrupo pequenoGrupo;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "past_id", referencedColumnName = "past_id")
	private Pastor pastor;

	/* TODO chamada.. */
	// @Getter @Setter
	// @OneToMany(mappedBy = "membro", fetch = FetchType.LAZY)
	// @Fetch(FetchMode.SELECT)
	// @Cascade(CascadeType.SAVE_UPDATE)
	// private List<Chamada> chamadas = new ArrayList<Chamada>();

	public Membro() {
	}

	public Long getId() {
		return this.idMembro;
	}

}
