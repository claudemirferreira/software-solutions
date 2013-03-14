package br.com.ss.centralaamar.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.User;

@Component
@Scope("session")
public class UserSession {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return user != null;
	}
}