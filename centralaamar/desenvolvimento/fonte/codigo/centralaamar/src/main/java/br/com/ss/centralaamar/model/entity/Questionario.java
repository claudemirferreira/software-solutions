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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questionario", catalog = "centralaamar")
public class Questionario extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4029046368610105833L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "quest_id", unique = true, nullable = false)
	private Long idQuestionario;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id" ,nullable = false)
	private Membro membro;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peq_id",nullable = false)
	private PequenoGrupo pequenoGrupo;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "saba_id",nullable = false)
	private Sabado sabado;
	
	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "perg_id",nullable = false)
	private Pergunta pergunta;

	@Getter @Setter
	@Column(name = "nome", unique=true,nullable = false)
	private String nome;

	public Long getId() {
		return idQuestionario;
	}

}
