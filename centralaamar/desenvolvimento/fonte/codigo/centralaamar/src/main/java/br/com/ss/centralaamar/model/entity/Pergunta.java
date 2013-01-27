package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "pergunta", catalog = "centralaamar")
public class Pergunta extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 845836539746268610L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "perg_id", unique = true, nullable = false)
	private Long idPergunta;

	@Getter @Setter
	@Column(name = "descricao", nullable = false, length = 100)
	private String descricao;
	
	public Pergunta() {
	}

	public Pergunta(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return this.idPergunta;
	}

}
