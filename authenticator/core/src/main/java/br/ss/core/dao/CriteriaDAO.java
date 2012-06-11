package br.ss.core.dao;


public class CriteriaDAO {

//	public void getUsuario() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Usuario> criteria = builder.createQuery( Usuario.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root );
//		
//		TypedQuery<Usuario> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getUsuarioByLogin() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Usuario> criteria = builder.createQuery( Usuario.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root );
//		
//		criteria.where( builder.like( root.get( Usuario_.login ) , "novoLogin" ),
//						builder.like( root.get( Usuario_.senha ) , "novoLogin" ));
//		
//		TypedQuery<Usuario> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getLogin() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<String> criteria = builder.createQuery( String.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root.get( Usuario_.login ) );
//		
//		TypedQuery<String> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getUsuarioLeftJoinPessoa() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Usuario> criteria = builder.createQuery( Usuario.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root );
//		
//		root.join( Usuario_.pessoas, JoinType.LEFT  );
//		
//		TypedQuery<Usuario> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getUsuarioJoinPessoa() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Usuario> criteria = builder.createQuery( Usuario.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root );
//		
//		Join<Usuario, Pessoa> joinPessoa = root.join( Usuario_.pessoas );
//		
//		criteria.where( builder.greaterThanOrEqualTo( joinPessoa.get( Pessoa_.idade) , 29 ) );
//		
//		TypedQuery<Usuario> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getLoginJoinPessoaDistinct() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<String> criteria = builder.createQuery( String.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root.get( Usuario_.login ) ).distinct( false );
//		
//		root.join( Usuario_.pessoas );
//		
//		
//		TypedQuery<String> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getLoginOrderBy() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<String> criteria = builder.createQuery( String.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root.get( Usuario_.login ) );
//		
//		criteria.orderBy( builder.asc( root.get( Usuario_.login ) ) );
//		
//		
//		TypedQuery<String> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getUsuarioIn() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<String> criteria = builder.createQuery( String.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root.get( Usuario_.login ) );
//		
//		List<String> logins = new ArrayList<String>();
//		logins.add("novoLogin");
//		logins.add("3");
//		
//		criteria.where( root.get( Usuario_.login ).in( logins ) );
//		
//		TypedQuery<String> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getLoginSenhaObjectArray() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Object[]> criteria = builder.createQuery( Object[].class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.multiselect( root.get( Usuario_.login ), root.get( Usuario_.senha ) );
//		
//		TypedQuery<Object[]> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getLoginSenhaAsClass() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Credentials> criteria = builder.createQuery( Credentials.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( builder.construct( Credentials.class, root.get( Usuario_.login ), root.get( Usuario_.senha )  ) );
//		
//		TypedQuery<Credentials> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//
//	public void getSubQuery() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Usuario> criteria = builder.createQuery( Usuario.class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		criteria.select( root );
//		
//		Subquery<Usuario> pessoaQuery = criteria.subquery( Usuario.class );
//		Root<Pessoa> pessoaRoot = pessoaQuery.from( Pessoa.class );
//		
//		pessoaQuery.select( pessoaRoot.get( Pessoa_.usuario ) );
//		pessoaQuery.where( builder.equal( pessoaRoot.get( Pessoa_.id ) , 4) );
//		
//		criteria.where( builder.equal( root , pessoaQuery) );
//		
//		TypedQuery<Usuario> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getGroupBy() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Object[]> criteria = builder.createQuery( Object[].class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		Join<Usuario, Pessoa> joinPessoa = root.join( Usuario_.pessoas );
//		
//		criteria.multiselect( root.get( Usuario_.login ), builder.count( joinPessoa.get( Pessoa_.usuario) ) );
//		
//		criteria.groupBy(  root.get( Usuario_.login ) );
//		criteria.having( builder.greaterThanOrEqualTo( builder.count( joinPessoa.get( Pessoa_.usuario) ) , 1L));
//		
//		TypedQuery<Object[]> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//	
//	public void getCase() {
//		EntityManager manager = JPAFactory.getInstance().getEntityManager();
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Object[]> criteria = builder.createQuery( Object[].class );
//		Root<Usuario> root = criteria.from( Usuario.class );
//		Join<Usuario, Funcao> joinFuncao = root.join( Usuario_.funcao, JoinType.LEFT );
//		
//		criteria.multiselect( root.get( Usuario_.login ), builder.selectCase().when( joinFuncao.isNull(), 1L).otherwise(2l) );
//		
//		TypedQuery<Object[]> query = manager.createQuery( criteria );
//		
//		query.getResultList();
//	}
//
	
}
