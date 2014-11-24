package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.MediaTurma;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.core.seguranca.servico.IService;

public interface DetalheBoletimServico extends IService<DetalheBoletim, Long> {
	
	public List<DetalheBoletim> listDetalheBoletimPorTurma(Turma turma);

	public List<MediaTurma> listaMediaTurma(Turma turma);
}