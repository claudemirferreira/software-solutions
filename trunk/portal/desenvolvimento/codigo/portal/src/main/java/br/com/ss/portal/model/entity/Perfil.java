package br.com.ss.portal.model.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entity implementation class for Entity: Perfil
 * 
 */
@Entity
@Table(name = "saa_perfil", catalog = "portal")
public class Perfil extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -1550723647595201191L;

	@Id
	@Column(name = "perfil_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfil;

	@Column(name = "perf_nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sistema_id", nullable = false)
	private Sistema sistema;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="perfil")
	@Fetch(FetchMode.SELECT)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<PerfilRotina> perfilRotinas = new HashSet<PerfilRotina>();

	
	
	public Perfil() {
		super();
	}

	@Override
	public Long getId() {
		return getIdPerfil();
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Set<PerfilRotina> getPerfilRotinas() {
		return perfilRotinas;
	}

	public void setPerfilRotinas(Set<PerfilRotina> perfilRotinas) {
		this.perfilRotinas = perfilRotinas;
	}
	
}
