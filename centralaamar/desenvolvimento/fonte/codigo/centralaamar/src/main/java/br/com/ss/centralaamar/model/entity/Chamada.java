package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chamada", catalog = "centralaamar")
public class Chamada extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 3970262043034919537L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cham_id", nullable = false)
	private Long idChamada;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id", nullable=false)
	private Membro membro;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peq_id", nullable=false)
	private PequenoGrupo pequenoGrupo;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "saba_id", nullable=false)
	private Sabado sabado;

	@Getter
	@Setter
	@Column(name = "presente")
	private Boolean presente;

	@Override
	public Long getId() {
		return getIdChamada();
	}

}
