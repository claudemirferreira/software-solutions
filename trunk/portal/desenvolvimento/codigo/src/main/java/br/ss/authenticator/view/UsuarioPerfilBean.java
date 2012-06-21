package br.ss.authenticator.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ss.authenticator.model.entity.UsuarioPerfil;
import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Usuario;

/**
 * Backing bean for UsuarioPerfil entities.
 * <p>
 * This class provides CRUD functionality for all UsuarioPerfil entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class UsuarioPerfilBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving UsuarioPerfil entities
    */

   private Integer id;

   public Integer getId()
   {
      return this.id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   private UsuarioPerfil usuarioPerfil;

   public UsuarioPerfil getUsuarioPerfil()
   {
      return this.usuarioPerfil;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.usuarioPerfil = this.search;
      }
      else
      {
         this.usuarioPerfil = this.entityManager.find(UsuarioPerfil.class, getId());
      }
   }

   /*
    * Support updating and deleting UsuarioPerfil entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.usuarioPerfil);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.usuarioPerfil);
            return "view?faces-redirect=true&id=" + this.usuarioPerfil.getIdUsuarioPerfil();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         this.entityManager.remove(this.entityManager.find(UsuarioPerfil.class, getId()));
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching UsuarioPerfil entities with pagination
    */

   private int page;
   private long count;
   private List<UsuarioPerfil> pageItems;

   private UsuarioPerfil search = new UsuarioPerfil();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public UsuarioPerfil getSearch()
   {
      return this.search;
   }

   public void setSearch(UsuarioPerfil search)
   {
      this.search = search;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<UsuarioPerfil> root = countCriteria.from(UsuarioPerfil.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<UsuarioPerfil> criteria = builder.createQuery(UsuarioPerfil.class);
      root = criteria.from(UsuarioPerfil.class);
      TypedQuery<UsuarioPerfil> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<UsuarioPerfil> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idUsuarioPerfil = this.search.getIdUsuarioPerfil();
      if (idUsuarioPerfil != 0)
      {
         predicatesList.add(builder.equal(root.get("idUsuarioPerfil"), idUsuarioPerfil));
      }
      Usuario usuario = this.search.getUsuario();
      if (usuario != null)
      {
         predicatesList.add(builder.equal(root.get("usuario"), usuario));
      }
      Perfil perfil = this.search.getPerfil();
      if (perfil != null)
      {
         predicatesList.add(builder.equal(root.get("perfil"), perfil));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<UsuarioPerfil> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back UsuarioPerfil entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<UsuarioPerfil> getAll()
   {

      CriteriaQuery<UsuarioPerfil> criteria = this.entityManager.getCriteriaBuilder().createQuery(UsuarioPerfil.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(UsuarioPerfil.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return UsuarioPerfilBean.this.entityManager.find(UsuarioPerfil.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((UsuarioPerfil) value).getIdUsuarioPerfil());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private UsuarioPerfil add = new UsuarioPerfil();

   public UsuarioPerfil getAdd()
   {
      return this.add;
   }

   public UsuarioPerfil getAdded()
   {
      UsuarioPerfil added = this.add;
      this.add = new UsuarioPerfil();
      return added;
   }
}