package br.com.ss;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Sistema;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.dominio.UsuarioPerfil;
import br.com.ss.academico.dominio.UsuarioPerfilPk;
import br.com.ss.academico.repositorio.PerfilRepositorio;
import br.com.ss.academico.repositorio.SistemaRepositorio;
import br.com.ss.academico.repositorio.UsuarioPerfilRepositorio;
import br.com.ss.academico.repositorio.UsuarioPerfilRepositorioSql;
import br.com.ss.academico.repositorio.UsuarioRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext.xml")
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
 @Ignore
public class UsuarioPerfilRepositoryTest {

	private static final String SISTEMA_COD = "SISTST";
	private static final String SISTEMA_TESTE = "Sistema Teste";
	private static final String PERFIL_TESTE = "Perfil Teste";
	private static final String USUARIO_TESTE = "Usuario Teste";


	@Autowired
	UsuarioPerfilRepositorioSql repositoryUPSql;

	@Autowired
	UsuarioPerfilRepositorio repositoryUP;
	
	@Autowired
	PerfilRepositorio repositoryPerfil;

	@Autowired
	UsuarioRepositorio repositoryUsuario;
	
	@Autowired
	SistemaRepositorio repositorySistema;
	
	Sistema sistema;

	private Perfil perfil;
	
	@Before
	public void init() {
		getSistema();
	}

	@Test
	public void testInsertByUsuarioPerfil() {
		
		Perfil perfil = getPerfil();
		Assert.assertNotNull(perfil);
		Assert.assertNotNull(perfil.getId());
		
		Usuario usuario = getUsuario();
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
		
		UsuarioPerfil usuarioPerfil = createUsuarioPerfil(perfil, usuario);

		Assert.assertNotNull(usuarioPerfil);
		
		UsuarioPerfil usuarioPerfilBD = repositoryUP.findByUsuarioAndPerfil(usuario, perfil);

		Assert.assertNotNull(usuarioPerfilBD);

		Assert.assertTrue( usuarioPerfil.getUsuarioPerfilPk().equals(usuarioPerfilBD.getUsuarioPerfilPk()) );

		Assert.assertTrue( usuarioPerfil.equals(usuarioPerfilBD ) );
		
	}

	@Test
	public void testInsertByUsuario() {
		
		Perfil perfil = getPerfil();
		Assert.assertNotNull(perfil);
		Assert.assertNotNull(perfil.getId());
		
		Usuario usuario = getUsuario();
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
		
		UsuarioPerfil usuarioPerfil = createUsuarioPerfil(perfil, usuario);
		
		usuario.getUsuarioPerfil().add(usuarioPerfil);
		repositoryUsuario.save(usuario);
		
		UsuarioPerfil usuarioPerfilBD = repositoryUP.findByUsuarioAndPerfil(usuario, perfil);

		Assert.assertNotNull(usuarioPerfilBD);

		Assert.assertTrue( usuarioPerfil.getUsuarioPerfilPk().equals(usuarioPerfilBD.getUsuarioPerfilPk()) );

		Assert.assertTrue( usuarioPerfil.equals(usuarioPerfilBD ) );

		
		UsuarioPerfil usuPerfil2 = usuario.getUsuarioPerfilByPerfil(perfil);
		
		Assert.assertNotNull(usuPerfil2);

		Assert.assertTrue( usuPerfil2.getUsuarioPerfilPk().equals(usuarioPerfilBD.getUsuarioPerfilPk()) );

		Assert.assertTrue( usuPerfil2.equals(usuarioPerfilBD ) );
		
	}


	@Test
	public void testSearch() {
		
		Perfil perfil = getPerfil();
		Assert.assertNotNull(perfil);
		Assert.assertNotNull(perfil.getId());
		
		Usuario usuario = getUsuario();
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
		
		UsuarioPerfil usuarioPerfil = createUsuarioPerfil(perfil, usuario);
		Assert.assertNotNull(usuarioPerfil);
		
		UsuarioPerfil usuarioPerfilBD = repositoryUP.findByUsuarioAndPerfil(usuario, perfil);

		Assert.assertNotNull(usuarioPerfilBD);

	}
	

	@Test
	public void testDelete() {
		
		Perfil perfil = getPerfil();
		Assert.assertNotNull(perfil);
		Assert.assertNotNull(perfil.getId());
		
		Usuario usuario = getUsuario();
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
		
		UsuarioPerfil usuarioPerfilBefore = repositoryUP.findByUsuarioAndPerfil(usuario, perfil);

		Assert.assertNotNull(usuarioPerfilBefore);
		
		repositoryUP.delete(usuarioPerfilBefore);
		
		UsuarioPerfil usuarioPerfilAfter = repositoryUP.findByUsuarioAndPerfil(usuario, perfil);

		Assert.assertNull(usuarioPerfilAfter);

	}


	private UsuarioPerfil createUsuarioPerfil(Perfil perfil, Usuario usuario) {
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
		usuarioPerfil.setData(new Date());
		
		usuarioPerfil.setUsuarioPerfilPk(new UsuarioPerfilPk());
		usuarioPerfil.setPerfil(perfil);
		usuarioPerfil.setUsuario(usuario);
		repositoryUP.save(usuarioPerfil);
		return usuarioPerfil;
	}
	
	private void getSistema() {
		sistema = repositorySistema.findByCodigo(SISTEMA_COD);
		if (sistema == null ) {
			createSistema();
		}
	}

	private Usuario getUsuario() {
		List<Usuario> usuarios = repositoryUsuario.findByNomeLike(USUARIO_TESTE);
		if ( usuarios.isEmpty() ) {
			return createUsuario();
		}
		return usuarios.get(0);
	}


	private Perfil getPerfil() {
		List<Perfil> perfis = repositoryPerfil.findByNomeLike(PERFIL_TESTE);
		if ( perfis.isEmpty() ) {
			return createPerfil();
		}
		return perfis.get(0);
	}

	private Perfil createPerfil() {
		perfil = new Perfil();
		perfil.setImagem(PERFIL_TESTE);
		perfil.setNome(PERFIL_TESTE);
		perfil.setSistema(sistema);
		repositoryPerfil.save(perfil);
		return perfil;
	}

	private Usuario createUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin(USUARIO_TESTE);
		usuario.setNome(USUARIO_TESTE);
//		usuario.setStatus("A");
		usuario.setSenha("teste");
		repositoryUsuario.save(usuario);
		return usuario;
	}

	private void createSistema() {
		sistema = new Sistema();
		sistema.setCodigo(SISTEMA_COD);
		sistema.setDescricao(SISTEMA_TESTE);
		sistema.setNome(SISTEMA_TESTE);
		sistema.setImagem(SISTEMA_TESTE);
		repositorySistema.save(sistema);
	}
	
}
