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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saa_perfil_rotina", uniqueConstraints = @UniqueConstraint(columnNames = {
		"perfil_id", "rotina_id" }))
public class PerfilRotina extends AbstractEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = -4082141067767212860L;

	@Getter
	@Setter
	@Id
	@Column(name = "perfil_rotina_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfilRotina;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "perfil_id", nullable = false)
	private Perfil perfil;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rotina_id", nullable = false)
	private Rotina rotina;

	public PerfilRotina() {
	}

	public Long getId() {
		return getIdPerfilRotina();
	}

}
