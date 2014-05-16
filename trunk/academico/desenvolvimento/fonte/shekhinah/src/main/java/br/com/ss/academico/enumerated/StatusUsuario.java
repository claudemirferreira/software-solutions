package br.com.ss.academico.enumerated;

public enum StatusUsuario {

	ATIVA(0, "ATIVO"), 
	INATIVA(1, "INATIVO"), 
	BLOQUEADO(2, "BLOQUEADO");

	private int id;
	private String descricao;

	private StatusUsuario(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static StatusUsuario getEnum(int id) {
		for (StatusUsuario sit : StatusUsuario.values()) {
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