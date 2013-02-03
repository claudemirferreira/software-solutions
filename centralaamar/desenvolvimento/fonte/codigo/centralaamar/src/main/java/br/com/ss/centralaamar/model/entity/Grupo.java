package br.com.ss.centralaamar.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "grupo", catalog = "centralaamar")
public class Grupo extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = -4116112636792954532L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "grup_id", nullable = false)
	private Long idGrupo;

	@Getter
	@Setter
	@Column(unique = true, nullable = false, length = 60)
	@NotNull
	private String nome;

	@Getter
	@Setter
	@OneToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Membro> membros = new ArrayList<Membro>();

	@Override
	public Long getId() {
		return getIdGrupo();
	}

}
