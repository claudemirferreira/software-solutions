package br.com.ss.portal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "saa_perfil_rotina", uniqueConstraints = @UniqueConstraint(columnNames = {
		"perfil_id", "rotina_id" }), catalog = "portal")
public class PerfilRotina extends AbstractEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = -4082141067767212860L;

	@Id
	@Column(name = "perfil_rotina_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfilRotina;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil_id", nullable = false)
	private Perfil perfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rotina_id", nullable = false)
	private Rotina rotina;

	public PerfilRotina() {
	}

	public Long getId() {
		return getIdPerfilRotina();
	}

	public Long getIdPerfilRotina() {
		return idPerfilRotina;
	}

	public void setIdPerfilRotina(Long idPerfilRotina) {
		this.idPerfilRotina = idPerfilRotina;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Rotina getRotina() {
		return rotina;
	}

	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
	}

}
