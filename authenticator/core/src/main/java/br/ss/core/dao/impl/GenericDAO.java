package br.ss.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.ss.core.dao.IGenericDAO;
import br.ss.core.model.entity.GenericEntity;
import br.ss.core.util.DateUtil;
import br.ss.core.util.StringUtil;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T extends GenericEntity, ID extends Serializable> 
									implements IGenericDAO<T, ID> {

	/**
     * classe que esta no contexto de persistencia.
     */
    private Class<T> persistentClass;

    /**
     * instancia da sessão.
     */
    @Inject	//hibernateSession
    private Session session;

    /**
     * Indica tipo de restricao na query.
     * 
     * @author dcoronel
     */
    enum RestrictionType {
        /**
         * Equals.
         */
        EQUALS( "=" ),
        
        /**
         * Like.
         */
        LIKE( "like" );
        
        /**
         * Guarda o correspondente a consulta no banco.
         */
        private String typeChar;
        
        private RestrictionType( String typeChar ) {
            this.typeChar = typeChar;
        }
        
        public String getTypeChar() {
            return typeChar;
        }
        
    }

    public GenericDAO() {
        this.persistentClass = (Class<T>) ( (ParameterizedType) this.getClass().getGenericSuperclass() )
                .getActualTypeArguments()[0];
    }

    public Session getSession() {
        return this.session;
    }

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    /**
     * procura pela chave primaria.
     */
    public T findByPrimaryKey( ID id, boolean lock ) {
        T entity;
        
        if ( lock ) {
            entity = (T) this.getSession().load( this.getPersistentClass(), id, LockMode.UPGRADE );
        } else {
            entity = (T) this.getSession().load( this.getPersistentClass(), id );
        }
        
        return entity;
    }

    /**
     * procura pela chave primaria de uma entidade.
     */
    public T findByPrimaryKey( ID id ) {
        return this.findByPrimaryKey( id, false );
    }

    /**
     * procura todos.
     */
    public List<T> findAll() {
        return this.findByCriteria();
    }

    /**
     * find by.
     */
    @SuppressWarnings( "unchecked" )
    public List<T> findByExample( T exampleInstance, String... excludeProperty ) {
        Criteria crit = this.getSession().createCriteria( this.getPersistentClass() );
        Example example = Example.create( exampleInstance );
        for ( String exclude : excludeProperty ) {
            example.excludeProperty( exclude );
        }
        crit.add( example );
        return crit.list();
    }

    /**
     * persite uma entidade.
     */
    public T persist( T entity ) {
        this.getSession().saveOrUpdate( entity );
        return entity;
    }

    /**
     * persite uma entidade.
     */
    public T persist( T entity, boolean flush ) {
        this.getSession().saveOrUpdate( entity );
        if ( flush ) {
            this.flush();
        }
        return entity;
    }

    /**
     * da merge em uma entidade.
     */
    @SuppressWarnings( "unchecked" )
    public T merge( T entity ) {
        return (T) this.getSession().merge( entity );
    }
    
    /**
     * da merge em uma entidade, com a possibilidade de realizar flush.
     */
    public T merge( T entity, boolean flush ) {
        this.getSession().merge( entity );
        if ( flush ) {
            this.flush();
        }
        return entity;
    }

    /**
     * da refresh em uma entidade.
     */
    public void refresh( T entity ) {
        this.getSession().refresh( entity );
    }

    /**
     * remove uma entidade.
     */
    public void remove( T entity ) {
        this.getSession().delete( entity );
    }

    /**
     * flush.
     */
    public void flush() {
        this.getSession().flush();
    }

    /**
     * Limpa a sessão.
     */
    public void clear() {
        this.getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings( "unchecked" )
    public List<T> findByCriteria( Criterion... criterion ) {
        Criteria crit = this.getSession().createCriteria( this.getPersistentClass() );
        for ( Criterion c : criterion ) {
            crit.add( c );
        }
        return crit.list();
    }

    /**
     * Realiza o count de linhas baseado nos parametros passados.
     * 
     * @param criterion
     * @return
     */
    public long count( Criterion... criterion ) {
        Criteria criteria = this.getSession().createCriteria( this.getPersistentClass() );
        for ( Criterion c : criterion ) {
            criteria.add( c );
        }
        criteria.setProjection( Projections.rowCount() );
        return ( (Integer) criteria.list().get( 0 ) ).intValue();
    }

    /**
     * Cria uma instancia de criteria baseada na entidade do dao.
     * 
     * @return
     */
    public Criteria createCriteria() {
        return this.getSession().createCriteria( this.persistentClass );
    }

    /**
     * Remove a entidade do gerenciamento.
     * 
     * @param entity
     */
    public void evict( T entity ) {
        this.getSession().evict( entity );
    }

    /**
     * metodo verifica se esta vazio ou não.
     * 
     * @param tx
     *            string
     * @return verdadeiro or false
     */
    protected boolean notEmpty( String tx ) {
        return StringUtil.notEmpty( tx );
    }

    /**
     * metodo verifica se esta vazio ou não.
     * 
     * @param tx
     *            short
     * @return verdadeiro or false
     */
    protected boolean notEmpty( Short tx ) {
        return ( tx != null ) && ( tx > 0 );
    }

    /**
     * metodo verifica se esta vazio ou não.
     * 
     * @param tx
     * @return verdadeiro or false
     */
    protected boolean notEmpty( Integer tx ) {
        return ( tx != null ) && ( tx > 0 );
    }

    /**
     * metodo verifica se esta vazio ou não.
     * 
     * @param tx
     * @return verdadeiro or false
     */
    protected boolean notEmpty( Object tx ) {
        return ( tx != null );
    }

    /**
     * Gera o código hql com as condições informadas.
     * 
     * <pre>
     * Ex:
     * * Select:
     *  <code>select ec from EntityClass </code>
     * * Condições:
     *  <code>where ec.cond1 = true and ec.cond2 = 1</code>
     * </pre> 
     * 
     * @param select O select da pesquisa
     * @param stmt Lista com as condiçoes de filtro da pesquisa (sem where e and)
     * @return String Hql gerado
     */
    protected String generateHql( String select, List<String> stmt ) {
        StringBuilder hql = new StringBuilder( select );
        boolean addedWhere = false;
        String where = " where ", and = " and ";
        for ( String s : stmt ) {
            if ( addedWhere ) {
                hql.append( and + s );
            } else {
                hql.append( where + s );
                addedWhere = true;
            }
        }
        return hql.toString();
    }
    
//    /**
//     * Retorna uma restricao utilizando toAscii.
//     * 
//     * @param alias
//     * @param atributo
//     * @param value
//     */
//    @Override
//    public Criterion iLiketoAscii( String atributo, String value ) {
//        return this.iLiketoAscii( "alias", atributo, value );
//    }

//    /**
//     * Retorna uma restricao utilizando toAscii.
//     * 
//     * @param alias
//     * @param atributo
//     * @param value
//     */
//    @Override
//    public Criterion iLiketoAscii( String alias, String atributo, String value ) {
//        String colunaNoDatabase = "";
//        try {
//            Method method = this.persistentClass.getMethod( "get" + atributo.substring( 0, 1 ).toUpperCase()
//                    + atributo.substring( 1 ) );
//            Column anottation = method.getAnnotation( Column.class );
//            colunaNoDatabase = anottation.name();
//        } catch ( SecurityException e ) {
//            e.printStackTrace();
//        } catch ( NoSuchMethodException e ) {
//            e.printStackTrace();
//        }
//
//        return Restrictions.sqlRestriction( "to_ascii({" + alias + "}." + colunaNoDatabase + ") ilike to_ascii( ? )",
//            "%" + value + "%", Hibernate.STRING );
//    }

    /**
     * Retorna uma restricao utilizando toAscii.
     * 
     * @param alias
     * @param atributo
     * @param value
     */
    @Override
    public Criterion toDate( String atributo, Date value, String condicao ) {
        return this.toDate( atributo, value, condicao, this.persistentClass );
    }

    /**
     * Retorna uma restricao utilizando toAscii.
     * 
     * @param alias
     * @param atributo
     * @param value
     */
    @Override
    public Criterion toDate( String atributo, Date value, String condicao, Class<?> clazz ) {
        String colunaNoDatabase = "";
        try {
            Method method = clazz
                    .getMethod( "get" + atributo.substring( 0, 1 ).toUpperCase() + atributo.substring( 1 ) );
            Column anottation = method.getAnnotation( Column.class );
            colunaNoDatabase = anottation.name();
        } catch ( SecurityException e ) {
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        }

        return Restrictions.sqlRestriction( "to_char({alias}." + colunaNoDatabase + ", 'yyyy/MM/dd' ) " + condicao
                + "'" + DateUtil.formatDateToString( value, "yyyy/MM/dd" ) + "'" );
    }

    /**
     * Retorna uma restricao utilizando toAscii.
     * 
     * @param alias
     * @param atributo
     * @param value
     */
    @Override
    public Criterion equalsIgnoreCaseToAscii( String atributo, String value ) {
        return ignoreCaseToAscii( atributo, value, RestrictionType.EQUALS );
    }
    
    /**
     * Retorna uma restricao utilizando toAscii.
     * 
     * @param alias
     * @param atributo
     * @param value
     */
    @Override
    public Criterion likeIgnoreCaseToAscii( String atributo, String value ) {
        return ignoreCaseToAscii( atributo, value, RestrictionType.LIKE );
    }
    
    /**
     * Retorna uma restricao utilizando toAscii.
     * 
     * @param alias
     * @param atributo
     * @param value
     */
    private Criterion ignoreCaseToAscii( String atributo, String value, RestrictionType type ) {
        String colunaNoDatabase = "";
        try {
            Method method = this.persistentClass.getMethod( "get" + atributo.substring( 0, 1 ).toUpperCase()
                    + atributo.substring( 1 ) );
            Column anottation = method.getAnnotation( Column.class );
            colunaNoDatabase = anottation.name();
        } catch ( SecurityException e ) {
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        }
        return Restrictions.sqlRestriction( "to_ascii(lower({alias}." + colunaNoDatabase + "))" 
             + type.getTypeChar() + " to_ascii( lower( ? ) )", value
                .toLowerCase(), null /*Hibernate.STRING TODO*/);
    }


    /**
     * Adicionado restricao.
     */
    public void addRestrictionEquals( Criteria criteria, String campo, Object value ) {
        this.addRestriction( criteria, campo, value, RestrictionType.EQUALS, false );
    }

    /**
     * Adicionado restricao.
     */
    public void addRestrictionEquals( Criteria criteria, String campo, Object value, boolean acceptNull ) {
        this.addRestriction( criteria, campo, value, RestrictionType.EQUALS, acceptNull );
    }

    /**
     * Adicionado restricao.
     */
    public void addRestriction( Criteria criteria, String campo, Object value, RestrictionType type, 
                                boolean acceptNull ) {
        if ( ( value != null ) || acceptNull ) {
            switch ( type ) {
                case EQUALS:
                    criteria.add( Restrictions.eq( campo, value ) );
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * Adiciona restrições fazendo like (utilizando 'OR') por partes do filtro informado.
     * 
     * @param entityProperty
     *            entidade e atributo a ser filtrado. Ex: diagnostico.txDescricaoPersonalizada
     * @param txFiltro
     *            texto a ser quebrado como filtro pro campo
     * @return String
     */
    public String addLikes( String entityProperty, String txFiltro ) {
        return this.addLikes( entityProperty, txFiltro, false );
    }

    /**
     * Adiciona restrições fazendo like por partes do filtro informado.
     * 
     * @param entityProperty
     *            entidade e atributo a ser filtrado. Ex: diagnostico.txDescricaoPersonalizada
     * @param txFiltro
     *            texto a ser quebrado como filtro pro campo
     * @param useAndInLike
     *            Indica se deve utilizar 'AND' inves de 'OR' no like
     * @return String
     */
    public String addLikes( String entityProperty, String txFiltro, boolean useAndInLike ) {
        String and = " and ";
        String or = " or ";
        StringBuilder cond = new StringBuilder();
        String[] likes = StringUtil.getStringsAsArray( txFiltro );
        int count = 0;
        if ( !useAndInLike ) {
            cond.append( and + " ( " );
            for ( String str : likes ) {
                cond.append( " lower(to_ascii( " + entityProperty + " )) like to_ascii( '%" + str.trim().toLowerCase()
                        + "%' ) " );
                if ( count < likes.length - 1 ) {
                    cond.append( or );
                }
                count++;
            }
            cond.append( " ) " );
        } else {
            cond.append( and + " ( " );
            cond.append( " lower(to_ascii( " + entityProperty + " )) like to_ascii( '%"
                    + txFiltro.trim().toLowerCase().replaceAll( " ", "%" ).replace( ".", "" ) + "%' ) " );
            cond.append( " ) " );
        }
        return cond.toString();
    }
}
