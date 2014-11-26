package br.fpf.components.web.menu;

import java.io.Serializable;

public class ItemMenu implements Serializable {

	private static final long serialVersionUID = 5905034532737728279L;

	private String id;

	private String descricao;

	private String link;

	private static int count;

	public ItemMenu(String id, String descricao, String link) {
		count++;
		this.id = id != null ? id : count + "";
		this.descricao = descricao;
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
