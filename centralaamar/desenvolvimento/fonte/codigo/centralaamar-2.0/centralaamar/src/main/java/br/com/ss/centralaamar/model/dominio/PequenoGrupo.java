package br.com.ss.centralaamar.model.dominio;

// Generated 02/12/2012 11:09:42 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Pequenogrupo generated by hbm2java
 */
@Entity
@Table(name = "cent_pequenogrupo")
public class PequenoGrupo extends AbstractEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = -4554552668548180993L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "peq_id", nullable = false)
	private Long idPequenoGrupo;

	@Column(unique = true, name = "nome", nullable = false, length = 100)
	// @NotNull
	private String nome;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "anfi_id", referencedColumnName = "pro_id")
	// @NotNull
	private Membro anfitriao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coord_id", referencedColumnName = "pro_id")
	// @NotNull
	private Membro coordenador;

	public PequenoGrupo() {
	}

	public PequenoGrupo(String nome) {
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return this.idPequenoGrupo;
	}

	public Long getIdPequenoGrupo() {
		return idPequenoGrupo;
	}

	public void setIdPequenoGrupo(Long idPequenoGrupo) {
		this.idPequenoGrupo = idPequenoGrupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Membro getAnfitriao() {
		return anfitriao;
	}

	public void setAnfitriao(Membro anfitriao) {
		this.anfitriao = anfitriao;
	}

	public Membro getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Membro coordenador) {
		this.coordenador = coordenador;
	}

}
