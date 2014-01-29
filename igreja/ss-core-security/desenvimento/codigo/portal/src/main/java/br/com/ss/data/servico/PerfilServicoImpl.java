package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Perfil;
import br.com.ss.data.repositories.PerfilRepositorio;

@Service
public class PerfilServicoImpl implements PerfilServico, Serializable {

	private static final long serialVersionUID = 3646593201944705310L;
	
	@Autowired
	private PerfilRepositorio PerfilRepositorio;

	@Override
	public List<Perfil> listarTodos() {
		return this.PerfilRepositorio.findAll();
	}

	@Override
	public Perfil salvar(Perfil perfil) {
		return this.PerfilRepositorio.save(perfil);
	}

	@Override
	public void remover(Perfil perfil) {
		this.PerfilRepositorio.delete(perfil);

	}
}
