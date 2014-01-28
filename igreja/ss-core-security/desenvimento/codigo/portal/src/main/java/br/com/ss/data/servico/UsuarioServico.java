package br.com.ss.data.servico;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ss.data.entities.Usuario;

public interface UsuarioServico {

	public List<Usuario> listarTodos();

	public Usuario salvar(Usuario usuario);

	public void remover(Usuario usuario);

	@Query("select u from usuario u where u.cpf = :cpf")
	public Usuario findByCpf(@Param("cpf") String cpf);

	@Query("select u from usuario u where u.cpf = :cpf and u.senha = :senha")
	public Usuario findByCpfAndSenha(@Param("cpf") String cpf,
			@Param("senha") String senha) throws NoResultException;
}
