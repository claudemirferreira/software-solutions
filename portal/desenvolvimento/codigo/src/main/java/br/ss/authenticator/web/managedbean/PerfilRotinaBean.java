package br.ss.authenticator.web.managedbean;

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

import br.ss.authenticator.model.entity.PerfilRotina;
import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Rotina;

/**
 * Backing bean for PerfilRotina entities.
 * <p>
 * This class provides CRUD functionality for all PerfilRotina entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class PerfilRotinaBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving PerfilRotina entities
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

   private PerfilRotina perfilRotina;

   public PerfilRotina getPerfilRotina()
   {
      return this.perfilRotina;
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
         this.perfilRotina = this.search;
      }
      else
      {
         this.perfilRotina = this.entityManager.find(PerfilRotina.class, getId());
      }
   }

   /*
    * Support updating and deleting PerfilRotina entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.perfilRotina);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.perfilRotina);
            return "view?faces-redirect=true&id=" + this.perfilRotina.getIdPerfilRotina();
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
         this.entityManager.remove(this.entityManager.find(PerfilRotina.class, getId()));
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
    * Support searching PerfilRotina entities with pagination
    */

   private int page;
   private long count;
   private List<PerfilRotina> pageItems;

   private PerfilRotina search = new PerfilRotina();

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

   public PerfilRotina getSearch()
   {
      return this.search;
   }

   public void setSearch(PerfilRotina search)
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
      Root<PerfilRotina> root = countCriteria.from(PerfilRotina.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<PerfilRotina> criteria = builder.createQuery(PerfilRotina.class);
      root = criteria.from(PerfilRotina.class);
      TypedQuery<PerfilRotina> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<PerfilRotina> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idPerfilRotina = this.search.getIdPerfilRotina();
      if (idPerfilRotina != 0)
      {
         predicatesList.add(builder.equal(root.get("idPerfilRotina"), idPerfilRotina));
      }
      Rotina rotina = this.search.getRotina();
      if (rotina != null)
      {
         predicatesList.add(builder.equal(root.get("rotina"), rotina));
      }
      Perfil perfil = this.search.getPerfil();
      if (perfil != null)
      {
         predicatesList.add(builder.equal(root.get("perfil"), perfil));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<PerfilRotina> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back PerfilRotina entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<PerfilRotina> getAll()
   {

      CriteriaQuery<PerfilRotina> criteria = this.entityManager.getCriteriaBuilder().createQuery(PerfilRotina.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(PerfilRotina.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return PerfilRotinaBean.this.entityManager.find(PerfilRotina.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((PerfilRotina) value).getIdPerfilRotina());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private PerfilRotina add = new PerfilRotina();

   public PerfilRotina getAdd()
   {
      return this.add;
   }

   public PerfilRotina getAdded()
   {
      PerfilRotina added = this.add;
      this.add = new PerfilRotina();
      return added;
   }
}