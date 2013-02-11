package br.com.ss.centralaamar.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Pergunta;

@Component
public class PerguntaDAO extends AbstractDAO<Pergunta> implements IPerguntaDAO {

	private static final long serialVersionUID = 7066212201002993951L;

	@Override
	public List<Pergunta> searchByEntity(Pergunta entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
