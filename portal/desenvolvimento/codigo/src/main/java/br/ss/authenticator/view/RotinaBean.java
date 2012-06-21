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

import br.ss.authenticator.model.entity.Rotina;
import br.ss.authenticator.model.entity.Sistema;

/**
 * Backing bean for Rotina entities.
 * <p>
 * This class provides CRUD functionality for all Rotina entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class RotinaBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Rotina entities
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

   private Rotina rotina;

   public Rotina getRotina()
   {
      return this.rotina;
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
         this.rotina = this.search;
      }
      else
      {
         this.rotina = this.entityManager.find(Rotina.class, getId());
      }
   }

   /*
    * Support updating and deleting Rotina entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.rotina);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.rotina);
            return "view?faces-redirect=true&id=" + this.rotina.getIdRotina();
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
         this.entityManager.remove(this.entityManager.find(Rotina.class, getId()));
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
    * Support searching Rotina entities with pagination
    */

   private int page;
   private long count;
   private List<Rotina> pageItems;

   private Rotina search = new Rotina();

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

   public Rotina getSearch()
   {
      return this.search;
   }

   public void setSearch(Rotina search)
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
      Root<Rotina> root = countCriteria.from(Rotina.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Rotina> criteria = builder.createQuery(Rotina.class);
      root = criteria.from(Rotina.class);
      TypedQuery<Rotina> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Rotina> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idRotina = this.search.getIdRotina();
      if (idRotina != 0)
      {
         predicatesList.add(builder.equal(root.get("idRotina"), idRotina));
      }
      Sistema sistema = this.search.getSistema();
      if (sistema != null)
      {
         predicatesList.add(builder.equal(root.get("sistema"), sistema));
      }
      String txRotina = this.search.getTxRotina();
      if (txRotina != null && !"".equals(txRotina))
      {
         predicatesList.add(builder.like(root.<String> get("txRotina"), '%' + txRotina + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Rotina> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Rotina entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Rotina> getAll()
   {

      CriteriaQuery<Rotina> criteria = this.entityManager.getCriteriaBuilder().createQuery(Rotina.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Rotina.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return RotinaBean.this.entityManager.find(Rotina.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Rotina) value).getIdRotina());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Rotina add = new Rotina();

   public Rotina getAdd()
   {
      return this.add;
   }

   public Rotina getAdded()
   {
      Rotina added = this.add;
      this.add = new Rotina();
      return added;
   }
}