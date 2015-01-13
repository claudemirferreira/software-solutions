package br.com.ss.academico.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.ss.academico.enumerated.ConceitoAvaliacao;
import br.com.ss.academico.enumerated.StatusBoletim;
import br.com.ss.core.seguranca.dominio.AbstractEntity;

@Entity
@Table(name = "acad_avaliacao_educ_infantil")
public class AvaliacaoEducacaoInfantil extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 5787909909249821360L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAvaliacaoEducacaoInfantil;

	@ManyToOne
	@JoinColumn(name = "id_boletim", nullable = false)
	private Boletim boletim;
	

	@ManyToOne
	@JoinColumn(name = "id_questao_avaliacao", nullable = false)
	private QuestaoAvaliacao questaoAvaliacao;
	
	
	@Enumerated
	@Column(length = 1)
	private StatusBoletim statusDisciplina = StatusBoletim.LANCAMENTO_PENDENTE;
	
	@Enumerated
	@Column(length = 1, name="conceito_avaliacao_1")
	private ConceitoAvaliacao conceitoAvaliacao1;

	@Enumerated
	@Column(length = 1, name="conceito_avaliacao_2")
	private ConceitoAvaliacao conceitoAvaliacao2;

	@Enumerated
	@Column(length = 1, name="conceito_avaliacao_3")
	private ConceitoAvaliacao conceitoAvaliacao3;

	@Enumerated
	@Column(length = 1, name="conceito_avaliacao_4")
	private ConceitoAvaliacao conceitoAvaliacao4;

	@Enumerated
	@Column(length = 1, name="conceito_geral")
	private ConceitoAvaliacao conceitoGeral;

	/** Para controlar quando nao resetar o status. */
	@Transient
	private boolean statusChanged;
	
	
	public AvaliacaoEducacaoInfantil() { }
	
	
	

	public void changeStatusDisciplina() {
		this.statusChanged = true;
		// FIXME continuar - fazer validacao..
	}
	
	
	
	/* --------------- Gets/Sets ------------------------ */

	@Override
	public Long getId() {
		return this.idAvaliacaoEducacaoInfantil;
	}

	public Long getIdAvaliacaoEducacaoInfantil() {
		return idAvaliacaoEducacaoInfantil;
	}

	public void setIdAvaliacaoEducacaoInfantil(Long idAvaliacaoEducacaoInfantil) {
		this.idAvaliacaoEducacaoInfantil = idAvaliacaoEducacaoInfantil;
	}

	public Boletim getBoletim() {
		return boletim;
	}

	public void setBoletim(Boletim boletim) {
		this.boletim = boletim;
	}

	public ConceitoAvaliacao getConceitoAvaliacao1() {
		return conceitoAvaliacao1;
	}

	public void setConceitoAvaliacao1(ConceitoAvaliacao conceitoAvaliacao1) {
		this.conceitoAvaliacao1 = conceitoAvaliacao1;
	}

	public ConceitoAvaliacao getConceitoAvaliacao2() {
		return conceitoAvaliacao2;
	}

	public void setConceitoAvaliacao2(ConceitoAvaliacao conceitoAvaliacao2) {
		this.conceitoAvaliacao2 = conceitoAvaliacao2;
	}

	public ConceitoAvaliacao getConceitoAvaliacao3() {
		return conceitoAvaliacao3;
	}

	public void setConceitoAvaliacao3(ConceitoAvaliacao conceitoAvaliacao3) {
		this.conceitoAvaliacao3 = conceitoAvaliacao3;
	}

	public ConceitoAvaliacao getConceitoAvaliacao4() {
		return conceitoAvaliacao4;
	}

	public void setConceitoAvaliacao4(ConceitoAvaliacao conceitoAvaliacao4) {
		this.conceitoAvaliacao4 = conceitoAvaliacao4;
	}

	public ConceitoAvaliacao getConceitoGeral() {
		return conceitoGeral;
	}

	public void setConceitoGeral(ConceitoAvaliacao conceitoGeral) {
		this.conceitoGeral = conceitoGeral;
	}




	public QuestaoAvaliacao getQuestaoAvaliacao() {
		return questaoAvaliacao;
	}




	public void setQuestaoAvaliacao(QuestaoAvaliacao questaoAvaliacao) {
		this.questaoAvaliacao = questaoAvaliacao;
	}




	public StatusBoletim getStatusDisciplina() {
		return statusDisciplina;
	}




	public void setStatusDisciplina(StatusBoletim statusDisciplina) {
		this.statusDisciplina = statusDisciplina;
	}

}