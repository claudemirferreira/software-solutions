package br.com.ss.academico.enumerated;

/**
 * Tipo de Curso
 * @author Robson
 */
public enum TipoCurso {
	
	EDUCACAO_INFANTIL(0, "Educação Infantil"),
	ENSINO_FUNDAMENTAL(1, "Ensino Fundamental");

	private final int id;
	private final String descricao;
	
	private TipoCurso(int id, String desc) {
		this.id = id;
		descricao = desc;
	}

	public static TipoCurso getEnum(int id) {
		for (TipoCurso fat : TipoCurso.values()) {
			if (fat.id == id)
				return fat;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
