package br.com.ss.centralaamar.model.entity;

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
@Table(name = "questionario", catalog = "centralaamar")
public class Questionario implements Serializable {

	private static final long serialVersionUID = -4029046368610105833L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "quest_id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id")
	private Membro membro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peq_id")
	private PequenoGrupo pequenoGrupo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "saba_id")
	private Sabado sabado;

	@Column(name = "descricao")
	private String descricao;

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Sabado getSabado() {
		return sabado;
	}

	public void setSabado(Sabado sabado) {
		this.sabado = sabado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
