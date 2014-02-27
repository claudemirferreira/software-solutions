package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the iansa_mensalidade database table.
 * 
 */
@Entity
@Table(name = "acad_mensalidade")
public class Mensalidade extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8538414139775767035L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long idMensalidade;

	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataVencimento;

	@Column(nullable = false)
	private int sequencial;

	private double valorPagamento;

	@Column(nullable = false)
	private double valorVencimento;

	// @ManyToOne
	// @JoinColumn(name = "matricula_id", nullable = false)
	// private Matricula matricula;

	public Mensalidade() {
	}
	
	public Long getId() {
		return this.idMensalidade;
	}

	public Long getIdMensalidade() {
		return this.idMensalidade;
	}

	public void setIdMensalidade(Long idMensalidade) {
		this.idMensalidade = idMensalidade;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getSequencial() {
		return this.sequencial;
	}

	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}

	public double getValorPagamento() {
		return this.valorPagamento;
	}

	public void setValorPagamento(double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public double getValorVencimento() {
		return this.valorVencimento;
	}

	public void setValorVencimento(double valorVencimento) {
		this.valorVencimento = valorVencimento;
	}

	// public Matricula getMatricula() {
	// return this.matricula;
	// }
	//
	// public void setMatricula1(Matricula matricula) {
	// this.matricula = matricula;
	// }

}