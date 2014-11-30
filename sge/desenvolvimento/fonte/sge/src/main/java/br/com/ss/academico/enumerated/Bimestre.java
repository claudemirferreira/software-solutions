package br.com.ss.academico.enumerated;

public enum Bimestre {

	PRIMEIRO(1, "1º Bimestre"), 
	SEGUNDO(2, "2º Bimestre"), 
	TERCEIRO(3,	"3º Bimestre"), 
	QUARTO(4, "4º Bimestre");

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