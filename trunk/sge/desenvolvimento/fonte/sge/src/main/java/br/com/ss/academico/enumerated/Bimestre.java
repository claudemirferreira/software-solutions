package br.com.ss.academico.enumerated;

public enum Bimestre {

	PRIMEIRO(1, "1 Bimestre"), 
	SEGUNDO(2, "2 Bimestre"), 
	TERCEIRO(3,	"3 Bimestre"), 
	QUARTO(4, "4 Bimestre");

	private int id;
	private String descricao;

	private Bimestre(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static Bimestre getEnum(int id) {
		for (Bimestre sit : Bimestre.values()) {
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