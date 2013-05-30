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
@Table(name = "saa_sistema", catalog = "portal")
public class Sistema extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 168661018940283398L;

	@Id
	@Column(name = "sistema_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSistema;

	@Column(name = "sis_nome", unique = true, nullable = false, length = 60)
	private String nome;

	@Column(name = "sis_descricao", nullable = true, length = 100)
	private String descricao;

	@Column(name = "sis_path", nullable = true, length = 100)
	private String path;

	@Column(name = "sis_imagem", nullable = true, length = 20)
	private String imagem;

	@Column(name = "sis_sigla", nullable = true, length = 30)
	private String sigla;

	@Override
	public Long getId() {
		return getIdSistema();
	}

	public Sistema() {
		super();
	}

	public Long getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
