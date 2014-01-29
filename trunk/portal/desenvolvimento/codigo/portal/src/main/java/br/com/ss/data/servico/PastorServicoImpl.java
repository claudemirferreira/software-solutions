package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Pastor;
import br.com.ss.data.repositories.PastorRepositorio;

@Service
public class PastorServicoImpl implements PastorServico, Serializable {

	private static final long serialVersionUID = -4614316993887353374L;
	
	@Autowired
	private PastorRepositorio pastorRepositorio;

	@Override
	public List<Pastor> listarTodos() {
		return this.pastorRepositorio.findAll();
	}

	@Override
	public Pastor salvar(Pastor perfil) {
		return this.pastorRepositorio.save(perfil);
	}

	@Override
	public void remover(Pastor perfil) {
		this.pastorRepositorio.delete(perfil);

	}
}