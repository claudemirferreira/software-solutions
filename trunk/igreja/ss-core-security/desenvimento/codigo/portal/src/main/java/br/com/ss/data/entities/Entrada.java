package br.com.ss.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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


/**
 * Entity implementation class for Entity: Entrada
 * 
 */
@Entity
@Table(name = "igre_entrada")
public class Entrada extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 168661018940283398L;

	@Id
	@Column(name = "id_entrada", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "valor_total", nullable = false)
	private float valorTotal;

	@Column(name = "data_entrada", nullable = false)
	private Date dataEntrada;

	@Column(name = "data_log", nullable = false)
	private Date dataLog;

	@Column(name = "observacao", length = 255 )
	private String observacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membro_id", nullable = false)
	private Membro membro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entrada")
	private Set<EntradaItem> entradaItems = new HashSet<EntradaItem>(0);
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataLog() {
		return dataLog;
	}

	public void setDataLog(Date dataLog) {
		this.dataLog = dataLog;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<EntradaItem> getEntradaItems() {
		return entradaItems;
	}

	public void setEntradaItems(Set<EntradaItem> entradaItems) {
		this.entradaItems = entradaItems;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}