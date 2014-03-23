package br.com.ss.centralaamar.model.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cent_questionario")
public class Questionario extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4029046368610105833L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "quest_id", unique = true, nullable = false)
	private Long idQuestionario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id", nullable = false)
	private Membro membro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peq_id", nullable = false)
	private PequenoGrupo pequenoGrupo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "saba_id", nullable = false)
	private Sabado sabado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "perg_id", nullable = false)
	private Pergunta pergunta;

	@Column(name = "nome", unique = true, nullable = false)
	private String nome;

	public Long getId() {
		return idQuestionario;
	}

	public Long getIdQuestionario() {
		return idQuestionario;
	}

	public void setIdQuestionario(Long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
	}

	public Sabado getSabado() {
		return sabado;
	}

	public void setSabado(Sabado sabado) {
		this.sabado = sabado;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
