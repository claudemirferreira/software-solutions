package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.PerfilRotina;
import br.com.ss.academico.dominio.Rotina;

public interface PerfilRotinaServico {

	public List<PerfilRotina> listarTodos();

	public PerfilRotina salvar(PerfilRotina usuarioPerfil);

	public void remover(PerfilRotina usuarioPerfil);

	public List<Rotina> listaRotinaNotInPerfil(Long idPerfil);
	
}
