package br.com.ss.academico.enumerated;

public enum GrauParentesco {

	PAI(0, "Pai"), 
	MAE(1, "Mãe"), 
	Avo(2, "Avô (Avó)"), 
	Tio(3, "Tio (Tia)"), 
	PADRASTO(4, "Padrasto (Madrasta)"), 
	OUTRO(5, "Outro");

	private int id;
	private String descricao;

	private GrauParentesco(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static GrauParentesco getEnum(int id) {
		for (GrauParentesco sit : GrauParentesco.values()) {
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