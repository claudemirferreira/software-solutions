package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Rotina;

public interface RotinaServico  extends IService<Rotina, Long> {

	public List<Rotina> listaRotinasPorPerfil(Long id);

	public List<Rotina> findByNomeLike(String nome);

}