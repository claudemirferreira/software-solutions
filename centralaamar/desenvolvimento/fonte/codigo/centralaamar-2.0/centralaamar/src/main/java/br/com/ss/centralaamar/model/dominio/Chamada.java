package br.com.ss.centralaamar.model.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cent_chamada")
public class Chamada extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 3970262043034919537L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cham_id", nullable = false)
	private Long idChamada;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id", nullable = false)
	private Membro membro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peq_id", nullable = false)
	private PequenoGrupo pequenoGrupo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "saba_id", nullable = false)
	private Sabado sabado;

	@Column(name = "presente")
	private Boolean presente;

	@Override
	public Long getId() {
		return getIdChamada();
	}

	public Long getIdChamada() {
		return idChamada;
	}

	public void setIdChamada(Long idChamada) {
		this.idChamada = idChamada;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
	}

	public Sabado getSabado() {
		return sabado;
	}

	public void setSabado(Sabado sabado) {
		this.sabado = sabado;
	}

	public Boolean getPresente() {
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

}
