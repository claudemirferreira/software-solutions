package br.com.ss.centralaamar.model.dao;

import java.util.List;

import br.com.ss.centralaamar.model.entity.User;

public interface UserDAO {
	List<User> findAll();

	void save(User user);

	void update(User user);

	void remove(User user);

	User getById(Long id);
	
	User login(String username, String password);
}