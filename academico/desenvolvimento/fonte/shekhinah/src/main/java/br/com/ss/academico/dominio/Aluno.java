package br.com.ss.academico.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the iansa_aluno database table.
 * 
 */
@Entity
@Table(name = "acad_aluno")
public class Aluno extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3496267061505047940L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAluno;

	@Column(nullable = false, length = 60)
	private String bairro;

	@Column(length = 8)
	private String celular;

	@Column(nullable = false, length = 8)
	private String cep;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(nullable = false, length = 1)
	private int sexo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	@Column(length = 60)
	private String email;

	@Column(nullable = false, length = 60)
	private String endereco;

	@Column(length = 8)
	private String foneResidencial;

	@Column(nullable = false, length = 60)
	private String nome;

	@Column(nullable = false, length = 11)
	private String rg;

	@Column(nullable = false, length = 11)
	private String grauParentesco;

	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;

	@OneToMany(mappedBy = "aluno", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Matricula> matriculas = new ArrayList<Matricula>();
	

	@Override
	public Long getId() {
		return this.idAluno;
	}

	public Long getIdAluno() {
		return this.idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
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

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
}