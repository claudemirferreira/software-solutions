package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cent_pergunta")
public class Pergunta extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 845836539746268610L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "perg_id", unique = true, nullable = false)
	private Long idPergunta;

	@Getter
	@Setter
	@Column(unique = true, name = "nome", nullable = false, length = 100)
	@NotNull
	private String nome;

	@Getter
	@Setter
	@Column(name = "status", nullable = false)
	@NotNull
	private Boolean status;

	public Pergunta() {
	}

	public Pergunta(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return this.idPergunta;
	}

}
