package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return entityManager.createQuery("FROM User").getResultList();
	}

	@Override
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	public void update(User user) {
		entityManager.persist(user);
	}

	@Override
	public void remove(User user) {
		user = getById(user.getId());
		entityManager.remove(user);
	}

	@Override
	public User getById(Long id) {
		return entityManager.find(User.class, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public User login(String username, String password) {
		String query = "FROM User u where u.username = ? and u.password=?";
		Query q = entityManager.createQuery(query);
		q.setParameter(1, username);
		q.setParameter(2, password);

		List l = q.getResultList();

		if (l == null || l.isEmpty() || l.size() > 1) {
			return null;
		}
		return (User) l.get(0);
	}
}