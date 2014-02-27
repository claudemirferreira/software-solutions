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
import javax.persistence.Transient;

/**
 * The persistent class for the acad_responsavel database table.
 * 
 */
@Entity
@Table(name = "acad_responsavel")
public class Responsavel extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3097639190127014748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idResponsavel;

	@Column(nullable = false, length = 60)
	private String bairro;

	@Column(length = 8)
	private String celular;

	@Column(nullable = false, length = 8)
	private String cep;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(nullable = false, length = 1)
	private String sexo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	@Column(length = 60)
	private String email;

	@Column(nullable = false, length = 60)
	private String endereco;

	@Column(length = 8)
	private String foneComercial;

	@Column(length = 8)
	private String foneResidencial;

	@Column(nullable = false, length = 60)
	private String nome;

	@Column(length = 30)
	private String profissao;

	@Column(nullable = false, length = 11)
	private String rg;

	@Transient
	private String dataInicioFormatada;

	@Transient
	private String dataFimFormatada;

	@Transient
	private String dataNascimentoFormatada;

	public Responsavel() {
	}

	@Override
	public Long getId() {
		return idResponsavel;
	}

	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getFoneComercial() {
		return foneComercial;
	}

	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataInicioFormatada() {
		return dataInicioFormatada;
	}

	public void setDataInicioFormatada(String dataInicioFormatada) {
		this.dataInicioFormatada = dataInicioFormatada;
	}

	public String getDataFimFormatada() {
		return dataFimFormatada;
	}

	public void setDataFimFormatada(String dataFimFormatada) {
		this.dataFimFormatada = dataFimFormatada;
	}

	public String getDataNascimentoFormatada() {
		return dataNascimentoFormatada;
	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}

}