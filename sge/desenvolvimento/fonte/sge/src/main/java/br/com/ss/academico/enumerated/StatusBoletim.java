package br.com.ss.academico.enumerated;

public enum StatusBoletim {

	LANCAMENTO_PENDENTE(0, "Lanç. Pendente"), 
	APROVADO(1, "Aprovado"), 
	REPROVADO(2, "Reprovado");

	private int id;
	private String descricao;

	private StatusBoletim(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static StatusBoletim getEnum(int id) {
		for (StatusBoletim sit : StatusBoletim.values()) {
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