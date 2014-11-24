package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.MediaTurma;
import br.com.ss.academico.dominio.Turma;

public interface DetalheBoletimRepositorioJPA {

	public List<DetalheBoletim> listaDetalheBoletimPorTurma(Turma turma);
	
	public List<MediaTurma> listaMediaTurma(Turma turma);

}