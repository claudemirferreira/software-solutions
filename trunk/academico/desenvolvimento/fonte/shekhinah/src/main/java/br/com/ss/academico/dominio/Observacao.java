package br.com.ss.academico.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acad_observacao")
public class Observacao extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1446099458543494149L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idObservacao;

	@Column(nullable = false, length = 255)
	private String txObservacao;

	@ManyToOne
	private Matricula matricula;
	
	
	@Override
	public Long getId() {
		return idObservacao;
	}

	public Long getIdObservacao() {
		return idObservacao;
	}

	public void setIdObservacao(Long idObservacao) {
		this.idObservacao = idObservacao;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public String getTxObservacao() {
		return txObservacao;
	}

	public void setTxObservacao(String txObservacao) {
		this.txObservacao = txObservacao;
	}
	
}
