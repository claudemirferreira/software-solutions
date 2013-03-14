package br.com.ss.centralaamar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.UserDAO;
import br.com.ss.centralaamar.model.entity.User;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserDAO dao;

	@Override
	public User login(String username, String password)
			throws IllegalArgumentException {
		if (isEmptyOrNull(username) || isEmptyOrNull(password)) {
			throw new IllegalArgumentException(
					"Atenção, username ou password vazios!");
		}
		User u = dao.login(username, password);

		if (u == null) {
			throw new IllegalArgumentException(
					"Erro: username ou password incorretos!");
		}
		return u;
	}

	private boolean isEmptyOrNull(String s) {
		return s == null || s.equals("");
	}
}