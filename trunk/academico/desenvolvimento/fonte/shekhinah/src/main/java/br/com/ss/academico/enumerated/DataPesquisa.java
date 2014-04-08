package br.com.ss.academico.enumerated;

public enum DataPesquisa {

	VECIMENTO(0, "Data vencimento"), PAGAMENTO(1, "Data pagamento");

	private int id;
	private String descricao;

	private DataPesquisa(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static DataPesquisa getEnum(int id) {
		for (DataPesquisa sit : DataPesquisa.values()) {
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