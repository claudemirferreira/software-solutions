package br.com.ss.data.entities;

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

/**
 * Entity implementation class for Entity: Perfil
 * 
 */
@Entity
@Table(name = "igre_saida")
public class Saida implements Serializable {

	private static final long serialVersionUID = -1550723647595201191L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "igre_nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_saida_id", nullable = false)
	private TipoEntrada tipoSaida;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoEntrada getTipoSaida() {
		return tipoSaida;
	}

	public void setTipoSaida(TipoEntrada tipoSaida) {
		this.tipoSaida = tipoSaida;
	}
}