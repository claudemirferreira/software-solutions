package br.com.ss.centralaamar.model.servico;

import java.util.List;

import br.com.ss.centralaamar.model.dominio.PerfilRotina;
import br.com.ss.centralaamar.model.dominio.Rotina;

public interface PerfilRotinaServico {

	public List<PerfilRotina> listarTodos();

	public PerfilRotina salvar(PerfilRotina usuarioPerfil);

	public void remover(PerfilRotina usuarioPerfil);

	public List<Rotina> listaRotinaNotInPerfil(Long idPerfil);

}
