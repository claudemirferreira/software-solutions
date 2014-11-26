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

import br.com.ss.academico.enumerated.Bimestre;
import br.com.ss.academico.enumerated.StatusBoletim;
import br.com.ss.academico.enumerated.TipoBoletim;
import br.com.ss.core.seguranca.dominio.AbstractEntity;
import br.com.ss.core.web.enumerated.NumeroUtil;

/**
 * @author Robson
 */
@Entity
@Table(name = "acad_detalhe_boletim")
public class DetalheBoletim extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -6438912749527248323L;

	@Column(name="nota_1")
	private Float nota1;

	@Column(name="nota_2")
	private Float nota2;

	@Column(name="nota_3")
	private Float nota3;

	@Column(name="nota_4")
	private Float nota4;

	@Column(name="nota_5")
	private Float nota5;

	@Column(name="nota_6")
	private Float nota6;

	@Column(name="nota_7")
	private Float nota7;

	@Column(name="nota_8")
	private Float nota8;
	

	@Column(name="media_1")
	private Float media1;

	@Column(name="media_2")
	private Float media2;

	@Column(name="media_3")
	private Float media3;

	@Column(name="media_4")
	private Float media4;

	@Column
	private Float mediaGeral;
	
	
	@Column(name="faltas_bimestre_1")
	private Integer faltasBimestre1 = 0;

	@Column(name="faltas_bimestre_2")
	private Integer faltasBimestre2 = 0;

	@Column(name="faltas_bimestre_3")
	private Integer faltasBimestre3 = 0;

	@Column(name="faltas_bimestre_4")
	private Integer faltasBimestre4 = 0;

	@Column
	private Integer totalFaltas = 0;
	
	@Column( columnDefinition = "BIT" )
	private boolean recuperacao;

	@Column
	private Float notaRecuperacao;
	

	@Column
	private Float mediaFinal;
	
	@Enumerated
	@Column(length = 1)
	private StatusBoletim statusDisciplina = StatusBoletim.LANCAMENTO_PENDENTE;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDetalheBoletim;

	@ManyToOne
	@JoinColumn(name = "id_disciplina", nullable = false)
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "id_boletim", nullable = false)
	private Boletim boletim;

	@Enumerated
	@Column(length = 1)
	private TipoBoletim tipoBoletim;
	
	private static final Float ZERO = 0.0f;
	
	/** Para controlar quando nao resetar o status. */
	@Transient
	public boolean statusChanged;
	
	
	public DetalheBoletim() { }

	
	public void calcularMedias( final Float mediaEscolar) {
		
		this.setMedia1((NumeroUtil.getFloat( this.nota1 ) + NumeroUtil.getFloat( this.nota2) ) / 2);
		this.setMedia2((NumeroUtil.getFloat( this.nota3 ) + NumeroUtil.getFloat( this.nota4) ) / 2);
		this.setMedia3((NumeroUtil.getFloat( this.nota5 ) + NumeroUtil.getFloat( this.nota6) ) / 2);
		this.setMedia4((NumeroUtil.getFloat( this.nota7 ) + NumeroUtil.getFloat( this.nota8) ) / 2);
		
		calcularTotalFaltas();
		
		if ( isLancadaNota4Bimestre() ) {
			
			// so calcula a media geral depois do 4º bim.
			calcularMediaGeral();
			
			if ( ! isEmRecuperacaoMediaGeral(mediaEscolar) ) {
				// se nao estiver de recuperacao reseta os dados finais de recuperacao
				resetMediasFinais(false, false);
			}
			
			calcularMediaFinal(mediaEscolar);
			
		} else {
			
			resetMediasFinais(true, true);
		}
	}

	/**
	 * Rreseta: medias final, geral, recuperacao e status.
	 */
	private void resetMediasFinais(boolean resetMediaGeral, boolean resetStatus) {
		if (resetMediaGeral) {
			mediaGeral = null;
		}
		mediaFinal = null;
		notaRecuperacao = null;
		recuperacao = false;
		if (resetStatus && !statusChanged) {
			statusDisciplina = StatusBoletim.LANCAMENTO_PENDENTE;
		}
	}


	private void calcularMediaGeral() {
		Float value = ( getMedia1() + getMedia2() + getMedia3() + getMedia4() ) / NumeroUtil.QUATRO;
		mediaGeral = NumeroUtil.arredondarValor(value);
	}
	

	private void calcularMediaFinal( final Float mediaEscolar ) {
		
		if ( isEmRecuperacaoMediaGeral(mediaEscolar) ) {
			// habilita a recuperacao se a media geral for menor que a media escolar
			recuperacao = true;
		}
		
		if ( recuperacao ) {
			Float valor = ( mediaGeral + NumeroUtil.getFloat( notaRecuperacao ) ) / NumeroUtil.DOIS;
			mediaFinal = NumeroUtil.arredondarValor(valor);
		} else {
			mediaFinal = mediaGeral;
		}
		
		atribuirStatusDisciplina(mediaEscolar);
		
	}

	/**
	 * Valida se a media geral é menor que a média escolar.
	 * @param mediaEscolar
	 * @return
	 */
	private boolean isEmRecuperacaoMediaGeral( final Float mediaEscolar ) {
		return mediaGeral < mediaEscolar;
	}


	/** atualiza o status se o aluno esta aprovado ou reprovado na disciplina. */
	private void atribuirStatusDisciplina(final Float mediaEscolar) {
		// valida se já foram lançadas todas as notas - ate o 4 bim.
		if ( isLancadaNota4Bimestre() ) {
			// valida as medias
			if ( mediaFinal < mediaEscolar
					// valida se excede o numero de faltas, mas nao muda pra reprovado 
					// se houve mudança pelo usuario
					|| ( excedeMaximoFaltas() && !statusChanged ) ) {
				statusDisciplina = StatusBoletim.REPROVADO;
			} else {
				statusDisciplina = StatusBoletim.APROVADO;
			}
		}
	}

	/**
	 * Valida se as notas do 4º bimestre ja foram lançadas
	 * @return
	 */
	public boolean isLancadaNota4Bimestre() {
		return nota7 != null && nota8 != null;
	}

	/**
	 * Valida se o total de faltas excede o maximo de faltas.
	 */
	private boolean excedeMaximoFaltas() {
		return totalFaltas != null && totalFaltas > disciplina.getMaximoFaltas();
	}
	

	private void calcularTotalFaltas() {
		totalFaltas = NumeroUtil.getInteger( faltasBimestre1 ) 
					+ NumeroUtil.getInteger( faltasBimestre2 )
					+ NumeroUtil.getInteger( faltasBimestre3 )
					+ NumeroUtil.getInteger( faltasBimestre4 );
	}
	
	
	public void changeStatusDisciplina() {
		this.statusChanged = true;
	}
	
	
	/* -------- Gets/Sets ---------------- */

	public boolean isRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(boolean recuperacao) {
		this.recuperacao = recuperacao;
		if ( !recuperacao ) {
			setNotaRecuperacao(0f);
		}
	}


	@Override
	public Long getId() {
		return idDetalheBoletim;
	}

	public Long getDetalheIdBoletim() {
		return idDetalheBoletim;
	}

	public void setIdDetalheBoletim(Long idDetalheBoletim) {
		this.idDetalheBoletim = idDetalheBoletim;
	}

	public Float getNota1() {
		return nota1;
	}

	public void setNota1(Float nota1) {
		this.nota1 = nota1;
		if (ZERO.equals(nota1)) {
			this.nota1 = null;
		}
	}

	public Float getNota2() {
		return nota2;
	}

	public void setNota2(Float nota2) {
		this.nota2 = nota2;
		if (ZERO.equals(nota2)) {
			this.nota2 = null;
		}
	}

	public Float getNota3() {
		return nota3;
	}

	public void setNota3(Float nota3) {
		this.nota3 = nota3;
		if (ZERO.equals(nota3)) {
			this.nota3 = null;
		}
	}

	public Float getNota4() {
		return nota4;
	}

	public void setNota4(Float nota4) {
		this.nota4 = nota4;
		if (ZERO.equals(nota4)) {
			this.nota4 = null;
		}
	}

	public Float getNota5() {
		return nota5;
	}

	public void setNota5(Float nota5) {
		this.nota5 = nota5;
		if (ZERO.equals(nota5)) {
			this.nota5 = null;
		}
	}

	public Float getNota6() {
		return nota6;
	}

	public void setNota6(Float nota6) {
		this.nota6 = nota6;
		if (ZERO.equals(nota6)) {
			this.nota6 = null;
		}
	}

	public Float getNota7() {
		return nota7;
	}

	public void setNota7(Float nota7) {
		this.nota7 = nota7;
		if (ZERO.equals(nota7)) {
			this.nota7 = null;
		}
	}

	public Float getNota8() {
		return nota8;
	}

	public void setNota8(Float nota8) {
		this.nota8 = nota8;
		if (ZERO.equals(nota8)) {
			this.nota8 = null;
		}
	}

	public Float getMedia1() {
		return media1;
	}

	public void setMedia1(Float media1) {
		this.media1 = media1;
	}

	public Float getMedia2() {
		return media2;
	}

	public void setMedia2(Float media2) {
		this.media2 = media2;
	}

	public Float getMedia3() {
		return media3;
	}

	public void setMedia3(Float media3) {
		this.media3 = media3;
	}

	public Float getMedia4() {
		return media4;
	}

	public void setMedia4(Float media4) {
		this.media4 = media4;
	}

	public Float getMediaFinal() {
		return mediaFinal;
	}

	public void setMediaFinal(Float mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Float getNotaRecuperacao() {
		return notaRecuperacao;
	}

	public void setNotaRecuperacao(Float notaRecuperacao) {
		this.notaRecuperacao = notaRecuperacao;
	}

	public StatusBoletim getStatusDisciplina() {
		return statusDisciplina;
	}

	public void setStatusDisciplina(StatusBoletim statusDisciplina) {
		this.statusDisciplina = statusDisciplina;
	}

	public Boletim getBoletim() {
		return boletim;
	}

	public void setBoletim(Boletim boletim) {
		this.boletim = boletim;
	}

	public Float getMediaGeral() {
		return mediaGeral;
	}

	public Integer getFaltasBimestre1() {
		return faltasBimestre1;
	}

	public void setFaltasBimestre1(Integer faltasBimestre1) {
		this.faltasBimestre1 = faltasBimestre1;
	}

	public Integer getFaltasBimestre2() {
		return faltasBimestre2;
	}

	public void setFaltasBimestre2(Integer faltasBimestre2) {
		this.faltasBimestre2 = faltasBimestre2;
	}

	public Integer getFaltasBimestre3() {
		return faltasBimestre3;
	}

	public void setFaltasBimestre3(Integer faltasBimestre3) {
		this.faltasBimestre3 = faltasBimestre3;
	}

	public Integer getFaltasBimestre4() {
		return faltasBimestre4;
	}

	public void setFaltasBimestre4(Integer faltasBimestre4) {
		this.faltasBimestre4 = faltasBimestre4;
	}


	public Integer getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(Integer totalFaltas) {
		this.totalFaltas = totalFaltas;
	}

	public Long getIdDetalheBoletim() {
		return idDetalheBoletim;
	}

	public void setMediaGeral(Float mediaGeral) {
		this.mediaGeral = mediaGeral;
	}
	
	public float pegarMedia(Bimestre bimestre){
		
		if (bimestre == Bimestre.PRIMEIRO)
			return getMedia1();
		else if (bimestre == Bimestre.SEGUNDO)
			return getMedia2();
		else if (bimestre == Bimestre.TERCEIRO)
			return getMedia3();
		else
			return getMedia4();
		
	}

}