package br.com.ss.centralaamar.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.model.entity.User;
import br.com.ss.centralaamar.service.LoginService;

@Controller
@Scope("request")
public class Authenticator implements AuthenticationProvider, Serializable{
	
	private static final long serialVersionUID = -9124773949815141523L;

	@Autowired
	private LoginService service;

	@Autowired
	private UserSession session;

	private String username;
	private String password;

	public String login() {
		try {
			User user = service.login(username, password);
			loginSpringSecurity(user);
			session.setUser(user);
			System.out.println("home.xhtml");
			return "/pages/successfulPage.xhtml";
		} catch (IllegalArgumentException ex) {
			message(ex.getMessage());
		}
		return "";
	}

	private void loginSpringSecurity(User user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getUsername(), null, user.getRoles());
		token.setDetails(user);

		SecurityContextHolder.createEmptyContext();
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		session.setUser(null);
		return "login.xhtml";
	}

	private void message(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(message));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

}