package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.ss.academico.enumerated.StatusBoletim;
import br.com.ss.core.seguranca.dominio.AbstractEntity;

@Entity
@Table(name = "acad_boletim")
public class Boletim extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -6438912749527248323L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBoletim;

	@Enumerated
	@Column(length = 1)
	private StatusBoletim statusBoletim = StatusBoletim.LANCAMENTO_PENDENTE;
	
	@ManyToOne
	@JoinColumn(name = "id_matricula", nullable = false)
	private Matricula matricula;

	@OneToMany(mappedBy = "boletim", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true )
	private Set<DetalheBoletim> detalheBoletims = new HashSet<DetalheBoletim>();


	@Transient
	private Integer totalFaltas1;
	@Transient
	private Integer totalFaltas2;
	@Transient
	private Integer totalFaltas3;
	@Transient
	private Integer totalFaltas4;
	
	@Transient
	private Integer totalGeral;

	@Transient
	private boolean recuperacao;
	
	public Boletim() { }


	/**
	 * Atualiza o boletim (final) do aluno.
	 * @param mediaEscolar
	 */
	public void atualizarBoletim(final Float mediaEscolar) {
		
		resetTotais();
		
		calcularMediasEFaltas(mediaEscolar);
		
		calcularTotalFaltas();
		
		atualizarStatusBoletim();
		
	}

	/**
	 * atualiza o status do boletim - se o aluno esta aprovado ou reprovado no ano letivo,
	 */
	private void atualizarStatusBoletim() {
		boolean lancTodasNotas = true;
		StatusBoletim statusTmp = StatusBoletim.APROVADO;
		for(DetalheBoletim det : detalheBoletims ) {
			if( !det.isLancadaNota4Bimestre() ) {
				lancTodasNotas = false;
			} else  {
				// verifica se o aluno ficou reprovado em alguma disciplina
				if(StatusBoletim.REPROVADO.equals( det.getStatusDisciplina()) ) { 
					statusTmp = StatusBoletim.REPROVADO;
				}
			}
		}
		
		if ( lancTodasNotas ) {
			statusBoletim = statusTmp;
		}
	}


	private void calcularMediasEFaltas(final Float mediaEscolar) {
		for(DetalheBoletim det : detalheBoletims ) {
			det.calcularMedias();
			det.calcularMediaFinal(mediaEscolar);
			if (det.isRecuperacao()) {
				recuperacao = true;
			}
			calcularFaltas(det);
		}
	}


	private void resetTotais() {
		totalFaltas1 = 0;
		totalFaltas2 = 0;
		totalFaltas3 = 0;
		totalFaltas4 = 0;
		totalGeral = 0;
		recuperacao = false;
	}


	private void calcularFaltas(DetalheBoletim det) {
		totalFaltas1 += det.getFaltasBimestre1();
		totalFaltas2 += det.getFaltasBimestre2();
		totalFaltas3 += det.getFaltasBimestre3();
		totalFaltas4 += det.getFaltasBimestre4();
	}
	

	private void calcularTotalFaltas() {
		totalGeral = totalFaltas1 + totalFaltas2 + totalFaltas3 + totalFaltas4;
	}

	
	
	
	/* -------- Gets/Sets ---------------- */
	@Override
	public Long getId() {
		return idBoletim;
	}

	public Long getIdBoletim() {
		return idBoletim;
	}

	public void setIdBoletim(Long idBoletim) {
		this.idBoletim = idBoletim;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Set<DetalheBoletim> getDetalheBoletims() {
		return detalheBoletims;
	}

	public void setDetalheBoletims(Set<DetalheBoletim> detalheBoletims) {
		this.detalheBoletims = detalheBoletims;
	}

	public StatusBoletim getStatusBoletim() {
		return statusBoletim;
	}

	public void setStatusBoletim(StatusBoletim statusBoletim) {
		this.statusBoletim = statusBoletim;
	}

	public Integer getTotalFaltas2() {
		return totalFaltas2;
	}

	public Integer getTotalFaltas1() {
		return totalFaltas1;
	}

	public Integer getTotalFaltas3() {
		return totalFaltas3;
	}

	public Integer getTotalFaltas4() {
		return totalFaltas4;
	}

	public Integer getTotalGeral() {
		return totalGeral;
	}

	public boolean isRecuperacao() {
		return recuperacao;
	}

}