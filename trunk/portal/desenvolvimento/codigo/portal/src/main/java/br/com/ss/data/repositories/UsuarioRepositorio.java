package br.com.ss.data.repositories;

import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ss.data.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.cpf = :cpf")
	public Usuario findByCpf(@Param("cpf") String cpf);

	@Query("select u from Usuario u where u.cpf = :cpf and u.senha = :senha")
	public Usuario findByCpfAndSenha(@Param("cpf") String cpf,
			@Param("senha") String senha) throws NoResultException;

}
