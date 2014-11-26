package br.com.ss.academico.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import br.com.ss.academico.enumerated.Bimestre;

@Entity
@Table(name = "acad_media_turma")
@Immutable
public class MediaTurma implements Serializable {

	private static final long serialVersionUID = -4074008626182897686L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_media_turma")
	private Long id;

	private float media1;

	private float media2;

	private float media3;

	private float media4;

	private String disciplina;

	public MediaTurma(Long id, String disciplina, float media1, float media2,
			float media3, float media4) {
		super();
		this.id = id;
		this.media1 = media1;
		this.media2 = media2;
		this.media3 = media3;
		this.media4 = media4;
		this.disciplina = disciplina;
	}

	public MediaTurma() {
	}

	public float getMedia1() {
		return media1;
	}

	public void setMedia1(float media1) {
		this.media1 = media1;
	}

	public float getMedia2() {
		return media2;
	}

	public void setMedia2(float media2) {
		this.media2 = media2;
	}

	public float getMedia3() {
		return media3;
	}

	public void setMedia3(float media3) {
		this.media3 = media3;
	}

	public float getMedia4() {
		return media4;
	}

	public void setMedia4(float media4) {
		this.media4 = media4;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float pegarMediaAcumulada(Bimestre bimestre) {

		if (bimestre == Bimestre.PRIMEIRO)
			return getMedia1();
		else if (bimestre == Bimestre.SEGUNDO)
			return (getMedia1() + getMedia2()) / 2;
		else if (bimestre == Bimestre.TERCEIRO)
			return (getMedia1() + getMedia2() + getMedia3()) / 3;
		else
			return (getMedia1() + getMedia2() + getMedia2() + getMedia3() + getMedia4()) / 4;
	}

	public float pegarMedia(Bimestre bimestre) {
		if (bimestre == Bimestre.PRIMEIRO)
			return getMedia1();
		else if (bimestre == Bimestre.SEGUNDO)
			return getMedia2();
		else if (bimestre == Bimestre.TERCEIRO)
			return getMedia3();
		else
			return getMedia4();
	}

}