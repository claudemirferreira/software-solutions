package br.com.ss.academico.enumerated;

/**
 * Conceito (pontuação) da Avaliação usado no boletim da Educação Infantil.
 * @author Robson
 */
public enum ConceitoAvaliacao {
	
	SIM(0, "S", "Sim"),
	NAO(1, "N", "Não"),
	AS_VEZES(2, "AV", "As Vezes"),
	EM_DESENVOLVIMENTO(0, "ED", "Em desenvolvimento"),
	NAO_TRABALHAMOS(0, "S", "Não trabalhamos");
	
	private final int id;
	private final String descricao;
	private final String sigla;

	private ConceitoAvaliacao(int id, String sigla, String desc ) {
		this.id = id;
		this.sigla = sigla;
		this.descricao = desc;
	}

	public static ConceitoAvaliacao getEnum(int id) {
		for (ConceitoAvaliacao fat : ConceitoAvaliacao.values()) {
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

	public String getSigla() {
		return sigla;
	}

}
