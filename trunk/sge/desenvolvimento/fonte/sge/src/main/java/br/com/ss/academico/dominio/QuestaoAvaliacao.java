package br.com.ss.academico.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ss.academico.enumerated.FatorAvaliacao;
import br.com.ss.core.seguranca.dominio.AbstractEntity;

//@Entity
//@Table(name = "acad_questao_avaliacao")
public class QuestaoAvaliacao extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2007492808185341812L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idQuestaoAvaliacao;

	@Column(name="tx_questao", nullable = false, length = 80)
	private String txQuestao;

	@Enumerated
	@Column(length = 1, nullable = false)
	private FatorAvaliacao fatorAvaliacao;

	public QuestaoAvaliacao() {
	}

	@Override
	public Long getId() {
		return this.idQuestaoAvaliacao;
	}

	public Long getIdQuestaoAvaliacao() {
		return idQuestaoAvaliacao;
	}

	public void setIdQuestaoAvaliacao(Long idQuestaoAvaliacao) {
		this.idQuestaoAvaliacao = idQuestaoAvaliacao;
	}

	public String getTxQuestao() {
		return txQuestao;
	}

	public void setTxQuestao(String txQuestao) {
		this.txQuestao = txQuestao;
	}

	public FatorAvaliacao getFatorAvaliacao() {
		return fatorAvaliacao;
	}

	public void setFatorAvaliacao(FatorAvaliacao fatorAvaliacao) {
		this.fatorAvaliacao = fatorAvaliacao;
	}

}