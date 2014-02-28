package br.com.ss.academico.enumerated;

public enum FatorRH {

	OPOSITIVO(0, "O+"), 
	APOSITIVO(1, "A+"), 
	BPOSITIVO(2, "B+"), 
	ONEGATIVO(3,"O-"), 
	ANEGATIVO(4, "A-"), 
	BNEGATIVO(5, "B-"), 
	ABPOSITIVO(6, "AB+"), 
	ABNEGATIVO(7, "AB-");

	private int id;
	private String descricao;

	private FatorRH(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static FatorRH getEnum(int id) {
		for (FatorRH sit : FatorRH.values()) {
			if (sit.id == id) {
				return sit;
			}
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