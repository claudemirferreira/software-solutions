package br.com.ss.core.seguranca.web.auth;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.UsuarioServico;

@Component
public class AuthenticationManager implements AuthenticationProvider {

	@Autowired
	private UsuarioServico usuarioServico;

//	private Usuario usuario;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		try {
			Usuario usuario = usuarioServico.findByLoginAndSenha(
										authentication.getName(), 
										authentication.getCredentials().toString() );
			
			
			

			if (usuario == null) {
				return null;
			}

			UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(
					usuario, null, new ArrayList<GrantedAuthority>() );

			SecurityContextHolder.getContext().setAuthentication( authenticatedUser );

			return authenticatedUser;

		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
