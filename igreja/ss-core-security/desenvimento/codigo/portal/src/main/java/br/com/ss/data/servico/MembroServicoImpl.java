package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Membro;
import br.com.ss.data.repositories.MembroRepositorio;

@Service
public class MembroServicoImpl implements MembroServico, Serializable {

	private static final long serialVersionUID = 2317486927458119916L;

	@Autowired
	private MembroRepositorio membroRepositorio;

	@Override
	public List<Membro> listarTodos() {
		return this.membroRepositorio.findAll();
	}

	@Override
	public Membro salvar(Membro membro) {
		return this.membroRepositorio.save(membro);
	}

	@Override
	public void remover(Membro membro) {
		this.membroRepositorio.delete(membro);
	}

	@Override
	public List<Membro> findByNome(String nome) {
		 return membroRepositorio.findByNomeLike("%"+nome+"%");
	}
	
}