package br.com.ss.centralaamar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Eleitor", catalog = "centralaamar")
public class Eleitor implements Serializable {

	private static final long serialVersionUID = -582851570491564936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ele_id")
	private long id;

	@Column(name = "nome", length = 60, nullable = false)
	@NotNull
	private String nome;

	@Column(name = "rua", length = 30)
	private String rua;
	@Column(name = "numero", length = 6)
	private String numero;
	@NotNull
	@Column(name = "secao", length = 4, nullable=false)
	private String secao;
	@NotNull
	@Column(name = "zona", length = 3, nullable=false)
	private String zona;
	@Column(name = "bairro", length = 30)
	private String bairro;
	@Column(name = "celular", length = 10)
	private String celular;
	@Column(name = "residencial", length = 10)
	private String residencial;
	@Column(name = "comercial", length = 10)
	private String comercial;
	@Column(name = "titulo", length = 15, nullable=false)
	@NotNull
	private String titulo;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loc_id")
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.SAVE_UPDATE)
	private Local local;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "res_id")
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.SAVE_UPDATE)
	private Responsavel responsavel;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Eleitor removeMask(Eleitor eleitor) {
		return eleitor;
	}

	public String toString() {
		return "id = " + getId() + "\n nome = " + getNome();
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}
