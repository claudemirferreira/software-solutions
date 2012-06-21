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

import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Sistema;

/**
 * Backing bean for Perfil entities.
 * <p>
 * This class provides CRUD functionality for all Perfil entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class PerfilBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Perfil entities
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

   private Perfil perfil;

   public Perfil getPerfil()
   {
      return this.perfil;
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
         this.perfil = this.search;
      }
      else
      {
         this.perfil = this.entityManager.find(Perfil.class, getId());
      }
   }

   /*
    * Support updating and deleting Perfil entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.perfil);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.perfil);
            return "view?faces-redirect=true&id=" + this.perfil.getIdPerfil();
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
         this.entityManager.remove(this.entityManager.find(Perfil.class, getId()));
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
    * Support searching Perfil entities with pagination
    */

   private int page;
   private long count;
   private List<Perfil> pageItems;

   private Perfil search = new Perfil();

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

   public Perfil getSearch()
   {
      return this.search;
   }

   public void setSearch(Perfil search)
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
      Root<Perfil> root = countCriteria.from(Perfil.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Perfil> criteria = builder.createQuery(Perfil.class);
      root = criteria.from(Perfil.class);
      TypedQuery<Perfil> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Perfil> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idPerfil = this.search.getIdPerfil();
      if (idPerfil != 0)
      {
         predicatesList.add(builder.equal(root.get("idPerfil"), idPerfil));
      }
      Sistema sistema = this.search.getSistema();
      if (sistema != null)
      {
         predicatesList.add(builder.equal(root.get("sistema"), sistema));
      }
      String txPerfil = this.search.getTxPerfil();
      if (txPerfil != null && !"".equals(txPerfil))
      {
         predicatesList.add(builder.like(root.<String> get("txPerfil"), '%' + txPerfil + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Perfil> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Perfil entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Perfil> getAll()
   {

      CriteriaQuery<Perfil> criteria = this.entityManager.getCriteriaBuilder().createQuery(Perfil.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Perfil.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return PerfilBean.this.entityManager.find(Perfil.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Perfil) value).getIdPerfil());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Perfil add = new Perfil();

   public Perfil getAdd()
   {
      return this.add;
   }

   public Perfil getAdded()
   {
      Perfil added = this.add;
      this.add = new Perfil();
      return added;
   }
}