package br.ss.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import lombok.Getter;
import br.ss.core.database.Database;
import br.ss.core.entity.AbstractEntity;

/**
 * Classe generica de DAO .
 *
 * @param <T>
 * @param <ID>
 */
@SuppressWarnings( { "unchecked", "rawtypes" } )
public abstract class AbstractDAO<D extends AbstractDAO<D, T>, T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Root<T> root;
	
	@Getter
	protected Class<T> persistentClass;
	protected CriteriaQuery<T> criteria;
	
	@Getter
	protected CriteriaBuilder qb;
	
	@Getter
	protected List<Predicate> restriction;
	
	private From<?, ?> join;
	protected boolean acceptNullValues = false;
	protected List<Order> orders;
	protected Class<?> toCustomSelection;
	private AbstractDAO<?,?> delegate;
	protected List<Path<?>> groups;

	@Inject @RequestScoped @Database
	protected EntityManager entityManager;

	
	public AbstractDAO() {
		initEntityClass();
	}
	
	public AbstractDAO( AbstractDAO<?,?> dao, SingularAttribute attribute ) {
		delegate = dao.getDelegate();
		join = dao.getRootOrJoin().join( attribute );
	}
	
	public AbstractDAO( AbstractDAO<?,?> dao, SetAttribute attribute ) {
		delegate = dao.getDelegate();
		join = dao.getRootOrJoin().join( attribute );
	}
	
	public AbstractDAO( AbstractDAO<?,?> dao, SetAttribute attribute, FetchType type ) {
		delegate = dao.getDelegate();
	}
	
	public AbstractDAO( AbstractDAO<?,?> dao, ListAttribute attribute ) {
		delegate = dao.getDelegate();
		join = dao.getRootOrJoin().join( attribute );
	}
	
	public D distinct() {
		criteria.distinct( true );
		return ( D ) this;
	}
	
	public T getByPrimaryKey( AbstractEntity entity ) {
		return entityManager.find(persistentClass, entity.getId() );
	}
	
	public T getByPrimaryKey( Long id ) {
		return entityManager.find(persistentClass, id );
	}
	
	public T merge( T entity ) {
		return entityManager.merge( entity );
	}
	
	public void remove( T entity ) {
        entityManager.remove( entity );
    }
	
	public void remove( Long id ) {
		getEntityManager().remove( getEntityManager().find( persistentClass, id ) );
	}
	
	public void persist( T entity ) {
        entityManager.persist( entity );
    }
	
	@PostConstruct
	void init() {
		if ( criteria == null && getDelegate() == this ) {
			restriction = new ArrayList<Predicate>();
			qb = getDelegate().entityManager.getCriteriaBuilder();
			criteria = getDelegate().qb.createQuery( persistentClass );
			root = getDelegate().criteria.from( persistentClass );
			groups = new ArrayList<Path<?>>();
			orders = new ArrayList<Order>();
		}
	}
	
	public CriteriaQuery<T> getCriteriaBuilder() {
		return criteria;
	}
	
	public D select() {
		getCriteriaBuilder().select( root );
		return (D) this;
	}
	
	public D select( Selection s ) {
		getCriteriaBuilder().select( s.get() );
		return (D) this;
	}

	/**
	 * Valores nulos serao inseridos na pesquisa do OR. 
	 * @param values
	 * @return
	 */
	
	public D or( Restriction<?,?,?> ... values ) {
		List<Predicate> orList = new ArrayList<Predicate>();
		for ( Restriction r : values ) {
			if ( !getDelegate().acceptNullValues && r.isNullValue() ) {
				continue;
			}
			orList.add( r.condition );
		}
		
		getDelegate().restriction.add( getDelegate().getBuilder().or( orList.toArray( new Predicate[0] ) ) );
		return (D) this;
	}
	
	private void addRestriction( Restriction<?,?,?> restriction ) {
		if ( !getDelegate().acceptNullValues && restriction.isNullValue() ) {
			return;
		}
		getDelegate().restriction.add( restriction.condition );
	}
	
	protected void clear() {
		getDelegate().orders = null;
		getDelegate().criteria = null;
		getDelegate().restriction = null;
		getDelegate().groups = null;
		init();
	}
	
	protected AbstractDAO<?,?> getDelegate() {
		if ( delegate == null ) {
			delegate = ( D ) this;
		}
		return delegate;
	}
	
	public CriteriaBuilder getBuilder() {
		getCriteriaBuilder();
		return getDelegate().qb;
	}
	
	public From getRootOrJoin() {
		if ( root != null ) {
			return root;
		}
		return join;
	}
	
	public void initEntityClass() {
		if ( getClass().getGenericSuperclass() instanceof ParameterizedType ) {
			ParameterizedType paramType = (ParameterizedType)  getClass().getGenericSuperclass();
			persistentClass = (Class<T>) paramType.getActualTypeArguments()[1];
		} else if ( getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedType ) {
			ParameterizedType paramType = (ParameterizedType)  getClass().getSuperclass().getGenericSuperclass();
			persistentClass = (Class<T>) paramType.getActualTypeArguments()[1];
		}
	}
	
	javax.persistence.criteria.Selection<?> customSelections[];
	public D only() {
		getCriteriaBuilder().select( (javax.persistence.criteria.Selection<? extends T>) customSelections[ 0 ] );
		return ( D ) this;
	}
	
	public D select( Restriction ... restrictions ) {
		customSelections = new javax.persistence.criteria.Selection<?>[ restrictions.length ];
		int i = 0;
		for ( Restriction res : restrictions ) {
			if ( res.lower ) {
				customSelections[ i++ ] = qb.lower( res.from.get( res.attribute ) );
			} else {
				customSelections[ i++ ] = res.from.get( res.attribute );
			}
		}
		
		return ( D ) this;
	}

	public D toCustomClass( Class<?> clazz ) {
		toCustomSelection = clazz;
		return ( D ) this;
	}
	
	protected D createCustomSelection() {
		getCriteriaBuilder().select( ( javax.persistence.criteria.Selection ) getDelegate().getBuilder().construct( toCustomSelection, customSelections ) );
		return ( D ) this;
	}

	//Nova Abordagem
	public D join( AbstractDAO ...restrictions  ) {
		return ( D ) this;
	}
	
	public D where( Restriction ...restrictions  ) {
		for ( Restriction<?,?,?> r : restrictions ) {
			addRestriction( r );
		}
		return ( D ) this;
	}
	
	public D groupBy( Restriction ...restrictions  ) {
		for ( Restriction<?,?,?> r : restrictions ) {
			groups.add(r.dao.getRootOrJoin().get( r.attribute ) );
		}
		
		return ( D ) this;
	}
	
	public D where( AbstractDAO dao  ) {
		return ( D ) this;
	}
	
	public D orderBy( Restriction<?,?,?> ... order ) {
		for ( Restriction res : order ) {
			if ( res.asc ) {
				if ( res.lower ) {
					orders.add( qb.asc( qb.lower( res.from.get( res.attribute ) ) )  );	
				} else {
					orders.add( qb.asc( res.from.get( res.attribute ) ) );
				}
			} else {
				if ( res.lower ) {
					orders.add( qb.desc( qb.lower( res.from.get( res.attribute ) ) ) );	
				} else {
					orders.add( qb.desc( res.from.get( res.attribute ) ) );
				}
			}
		}
		return ( D ) this;
	}
	
    public D orderByAsc( SingularAttribute  attr ) {
        orders.add( getDelegate().qb.asc( getRootOrJoin().get( attr ) ) );
        return (D) this;
    }
	
	public D orderByDesc( SingularAttribute attr ) {
		orders.add( getDelegate().qb.desc( getRootOrJoin().get( attr ) ) );
        return (D) this;
    }
	
	public D acceptNullValues() {
		getDelegate().acceptNullValues = true;
		return (D) this;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public D withNull( SingularAttribute<?, ?> attribute ) {
		getDelegate().restriction.add( getRootOrJoin().get( attribute ).isNull() );
		return ( D ) this;
	}
	
	public D withNotNull( SingularAttribute<?, ?> attribute ) {
		getDelegate().restriction.add( getRootOrJoin().get( attribute ).isNotNull() );
		return ( D ) this;
	}
	
	public D fetch( SingularAttribute<?, ?> attribute ) {
		getRootOrJoin().fetch( attribute );
		return ( D ) this;
	}
	
	public D fetch( PluralAttribute attribute ) {
		getRootOrJoin().fetch( attribute );
		return ( D ) this;
	}
	
	public T getReference( AbstractEntity entity ) {
		return entityManager.getReference( persistentClass, entity.getId() );
	}
	
} 