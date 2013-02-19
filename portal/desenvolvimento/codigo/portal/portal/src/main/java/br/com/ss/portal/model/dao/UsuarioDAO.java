package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Usuario;

@Repository
public class UsuarioDAO extends AbstractDAO<Usuario> implements IUsuarioDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public Usuario getByPrimaryKey(Usuario entity) {
		return super.getByPrimaryKey(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> searchByEntity(Usuario entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Usuario p ");
		if (notEmpty(entity.getNome())) {
			condictions.add(" lower(p.nome) like :nome ");
		}

		String orderBy = " order by p.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity.getNome())) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase()
					+ "%");
		}

		return q.getResultList();

	}

	@Override
	public Usuario logar(Usuario usuario) {
		Usuario u;
		
		StringBuilder s = new StringBuilder();

		s.append(" select u from Usuario u ");
		s.append(" where u.cpf = :cpf ");
		s.append(" and u.senha = :senha ");

		System.out.println(s.toString());
		Query q = this.entityManager.createQuery(s.toString());
		q.setParameter("cpf", usuario.getCpf());
		q.setParameter("senha", usuario.getSenha());
		
		System.out.println("cpf == "+ usuario.getCpf());
		System.out.println("senha == "+ usuario.getSenha());

		try {

			u = (Usuario) q.getSingleResult();
			return u;

		} catch (NoResultException nore) {
			System.out.println("==========================");
			System.out.println("login e/ou senha invalidos");
			System.out.println("==========================");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnning",
							"Login e senha invalidos"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Ocorreu um erro no sistema"));
		}
		return null;

	}

}