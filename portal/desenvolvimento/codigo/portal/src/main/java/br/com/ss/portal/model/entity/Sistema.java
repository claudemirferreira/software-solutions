package br.com.ss.portal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Sistema
 * 
 */
@Entity
@Table(name = "saa_sistema", catalog="portal")
public class Sistema extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 168661018940283398L;

	@Getter
	@Setter
	@Id
	@Column(name = "sistema_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSistema;

	@Getter
	@Setter
	@Column(name = "sis_nome", unique = true, nullable = false, length = 60)
	private String nome;

	@Getter
	@Setter
	@Column(name = "sis_descricao", nullable = true, length = 100)
	private String descricao;

	@Getter
	@Setter
	@Column(name = "sis_path", nullable = true, length = 100)
	private String path;

	@Getter
	@Setter
	@Column(name = "sis_imagem", nullable = true, length = 20)
	private String imagem;
	
	@Getter
	@Setter
	@Column(name = "sis_sigla", nullable = true, length = 30)
	private String sigla;

	@Override
	public Long getId() {
		return getIdSistema();
	}

	public Sistema() {
		super();
	}

}
