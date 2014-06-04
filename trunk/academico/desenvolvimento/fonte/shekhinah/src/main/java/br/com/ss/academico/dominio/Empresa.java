package br.com.ss.academico.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ss.core.seguranca.dominio.AbstractEntity;

/**
 * The persistent class for the iansa_curso database table.
 * 
 */

@Entity
@Table(name = "acad_empresa")
public class Empresa extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 6622307844544139433L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEmpresa;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = false, length = 14, unique = true)
	private String cnpj;

	@Column(length = 60)
	private String email;

	@Column(length = 60)
	private String endereco;

	@Column(length = 60)
	private String bairro;
	
	@Column(length = 10)
	private String fone;
	
	@Column(length = 10)
	private String celular;
	

	public Empresa() {
	}

	public Long getId() {
		return this.idEmpresa;
	}

	public Long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdCurso(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}