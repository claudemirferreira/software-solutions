package br.com.ss.centralaamar.service;

import br.com.ss.centralaamar.model.entity.User;

public interface LoginService {
	User login(String username, String password)
			throws IllegalArgumentException;
}