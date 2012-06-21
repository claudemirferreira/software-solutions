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

import br.ss.authenticator.model.entity.Usuario;

/**
 * Backing bean for Usuario entities.
 * <p>
 * This class provides CRUD functionality for all Usuario entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class UsuarioBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Usuario entities
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

   private Usuario usuario;

   public Usuario getUsuario()
   {
      return this.usuario;
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
         this.usuario = this.search;
      }
      else
      {
         this.usuario = this.entityManager.find(Usuario.class, getId());
      }
   }

   /*
    * Support updating and deleting Usuario entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.usuario);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.usuario);
            return "view?faces-redirect=true&id=" + this.usuario.getIdUsuario();
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
         this.entityManager.remove(this.entityManager.find(Usuario.class, getId()));
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
    * Support searching Usuario entities with pagination
    */

   private int page;
   private long count;
   private List<Usuario> pageItems;

   private Usuario search = new Usuario();

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

   public Usuario getSearch()
   {
      return this.search;
   }

   public void setSearch(Usuario search)
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
      Root<Usuario> root = countCriteria.from(Usuario.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
      root = criteria.from(Usuario.class);
      TypedQuery<Usuario> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Usuario> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int idUsuario = this.search.getIdUsuario();
      if (idUsuario != 0)
      {
         predicatesList.add(builder.equal(root.get("idUsuario"), idUsuario));
      }
      String txLogin = this.search.getTxLogin();
      if (txLogin != null && !"".equals(txLogin))
      {
         predicatesList.add(builder.like(root.<String> get("txLogin"), '%' + txLogin + '%'));
      }
      String txEmail = this.search.getTxEmail();
      if (txEmail != null && !"".equals(txEmail))
      {
         predicatesList.add(builder.like(root.<String> get("txEmail"), '%' + txEmail + '%'));
      }
      String txSenha = this.search.getTxSenha();
      if (txSenha != null && !"".equals(txSenha))
      {
         predicatesList.add(builder.like(root.<String> get("txSenha"), '%' + txSenha + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Usuario> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Usuario entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Usuario> getAll()
   {

      CriteriaQuery<Usuario> criteria = this.entityManager.getCriteriaBuilder().createQuery(Usuario.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Usuario.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return UsuarioBean.this.entityManager.find(Usuario.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Usuario) value).getIdUsuario());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Usuario add = new Usuario();

   public Usuario getAdd()
   {
      return this.add;
   }

   public Usuario getAdded()
   {
      Usuario added = this.add;
      this.add = new Usuario();
      return added;
   }
}