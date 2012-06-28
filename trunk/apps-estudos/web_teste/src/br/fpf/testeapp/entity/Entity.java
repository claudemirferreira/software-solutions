package br.fpf.testeapp.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class Entity {

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private boolean ativo;

	@Getter @Setter
	private Set<Detail> details = new HashSet<Detail>();
	
	public Entity() {
	}
	
	public Entity(String nome, boolean ativo) {
		super();
		this.nome = nome;
		this.ativo = ativo;
	}
	
}
