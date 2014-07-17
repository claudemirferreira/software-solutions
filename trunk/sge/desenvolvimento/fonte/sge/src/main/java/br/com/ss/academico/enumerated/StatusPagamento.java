package br.com.ss.academico.enumerated;

public enum StatusPagamento {

	PENDENTE(0, "PENDENTE"), PAGO(1, "PAGO"), CANCELADO(2, "CANCELADO");

	private int id;
	private String descricao;

	private StatusPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static StatusPagamento getEnum(int id) {
		for (StatusPagamento sit : StatusPagamento.values()) {
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