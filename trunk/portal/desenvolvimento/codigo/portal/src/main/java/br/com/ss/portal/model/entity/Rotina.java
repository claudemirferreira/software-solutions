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

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Rotina
 * 
 */
@Entity
@Table(name = "saa_rotina", catalog="portal")
public class Rotina extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 5078104430987992410L;

	@Getter
	@Setter
	@Id
	@Column(name = "rotina_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRotina;

	@Getter
	@Setter
	@Column(name = "rot_nome", nullable = false, length = 60)
	private String nome;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	@Getter
	@Setter
	@Column(name = "rot_status")
	private Boolean status;

	@Getter
	@Setter
	@Column(name = "rot_path", nullable = false, length = 60)
	private String path;

	public Rotina() {
		super();
	}

	@Override
	public Long getId() {
		return getIdRotina();
	}

}
