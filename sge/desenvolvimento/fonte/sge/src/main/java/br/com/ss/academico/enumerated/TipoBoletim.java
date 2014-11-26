package br.com.ss.academico.enumerated;

/**
 * Tipo de boletim
 * @author Robson
 */
public enum TipoBoletim {
	
	EDUCACAO_INFANTIL(0, "Educação Infantil"),
	FUNDAMENTAL(0, "Fundamental");

	private final int id;
	private final String descricao;
	
	private TipoBoletim(int id, String desc) {
		this.id = id;
		descricao = desc;
	}

	public static TipoBoletim getEnum(int id) {
		for (TipoBoletim fat : TipoBoletim.values()) {
			if (fat.id == id)
				return fat;
		}
		return null;
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
