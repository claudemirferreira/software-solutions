package br.com.ss.academico.enumerated;

/**
 * Fator de Avaliação usado no boletim da Educação Infantil.
 * @author Robson
 */
public enum FatorAvaliacao {
	
	FATORES_VISOMOTORES(0, "FATORES VISOMOTORES"),
	FATORES_DE_COMPORTAMENTO_ESCOLAR(1, "FATORES DE COMPORTAMENTO ESCOLAR"),
	DESENVOLVIMENTO_SOCIO_EMOCIONAL(2,"DESENVOLVIMENTO SÓCIO-EMOCIONAL");
	
	private final int id;
	
	private final String descricao;

	private FatorAvaliacao(int id, String desc ) {
		this.id = id;
		this.descricao = desc;
	}

	public static FatorAvaliacao getEnum(int id) {
		for (FatorAvaliacao fat : FatorAvaliacao.values()) {
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
