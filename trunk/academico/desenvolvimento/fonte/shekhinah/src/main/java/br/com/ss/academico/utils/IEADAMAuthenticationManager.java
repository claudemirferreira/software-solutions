package br.com.ss.academico.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.servico.UsuarioServico;

@Component
public class IEADAMAuthenticationManager implements AuthenticationProvider {

	@Autowired
	private UsuarioServico usuarioServico;

	private Usuario usuario;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		try {
			this.usuario = usuarioServico.findByLoginAndSenha(authentication
					.getName(), authentication.getCredentials().toString());

			if (this.usuario == null) {
				return null;
			}

			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

			UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(
					usuario, null, grantedAuthorities);

			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);

			return authenticatedUser;

		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
