package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ss.academico.enumerated.StatusPagamento;

/**
 * The persistent class for the iansa_mensalidade database table.
 * 
 */

@Entity
@Table(name = "acad_mensalidade")
public class Mensalidade extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 73351027590035641L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idMensalidade;

	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataVencimento;

	@Column(nullable = false)
	private Integer sequencial;

	private double valorPagamento;

	@Column(nullable = false)
	private double valorVencimento;

	@Column(nullable = false, length = 1)
	private StatusPagamento statusPagamento;

	@ManyToOne
	@JoinColumn(name = "id_matricula", nullable = false)
	private Matricula matricula;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Mensalidade() {
		Matricula matricula = new Matricula();
		matricula.setAluno(new Aluno());
		matricula.setTurma(new Turma());
		this.setMatricula(matricula);

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

	public Integer getSequencial() {
		return this.sequencial;
	}

	public void setSequencial(Integer sequencial) {
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

	public Matricula getMatricula() {
		return this.matricula;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

}