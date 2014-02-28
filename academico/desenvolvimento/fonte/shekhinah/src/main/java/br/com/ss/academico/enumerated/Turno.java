package br.com.ss.academico.enumerated;

public enum Turno {

	MATUTINO(0, "MATUTINO"), VESPERTINO(1, "VESPERTINO"), INTEGRAL(1,
			"INTEGRAL");

	private int id;
	private String descricao;

	private Turno(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static Turno getEnum(int id) {
		for (Turno sit : Turno.values()) {
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