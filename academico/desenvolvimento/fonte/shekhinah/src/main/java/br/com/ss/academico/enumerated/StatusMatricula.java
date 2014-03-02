package br.com.ss.academico.enumerated;

public enum StatusMatricula {

	ATIVA(0, "ATIVA"), INATIVA(1, "INATIVA"), CANCELADA(2, "CANCELADA");

	private int id;
	private String descricao;

	private StatusMatricula(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static StatusMatricula getEnum(int id) {
		for (StatusMatricula sit : StatusMatricula.values()) {
			if (sit.id == id)
				return sit;
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return this.descricao;
	}
}