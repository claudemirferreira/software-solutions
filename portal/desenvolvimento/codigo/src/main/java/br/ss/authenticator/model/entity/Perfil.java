package br.ss.authenticator.model.entity;

// Generated 18/06/2012 13:32:57 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Perfil generated by hbm2java
 */
@Entity
@Table(name = "perfil", schema = "authenticator", uniqueConstraints = @UniqueConstraint(columnNames = "tx_perfil"))
public class Perfil implements java.io.Serializable
{

   private int idPerfil;
   private Sistema sistema;
   private String txPerfil;
   private Set<PerfilRotina> perfilRotinas = new HashSet<PerfilRotina>(0);
   private Set<UsuarioPerfil> usuarioPerfils = new HashSet<UsuarioPerfil>(0);

   public Perfil()
   {
   }

   public Perfil(int idPerfil, Sistema sistema)
   {
      this.idPerfil = idPerfil;
      this.sistema = sistema;
   }

   public Perfil(int idPerfil, Sistema sistema, String txPerfil, Set<PerfilRotina> perfilRotinas, Set<UsuarioPerfil> usuarioPerfils)
   {
      this.idPerfil = idPerfil;
      this.sistema = sistema;
      this.txPerfil = txPerfil;
      this.perfilRotinas = perfilRotinas;
      this.usuarioPerfils = usuarioPerfils;
   }

   @Id
   @Column(name = "id_perfil", unique = true, nullable = false)
   public int getIdPerfil()
   {
      return this.idPerfil;
   }

   public void setIdPerfil(int idPerfil)
   {
      this.idPerfil = idPerfil;
   }

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_sistema", nullable = false)
   public Sistema getSistema()
   {
      return this.sistema;
   }

   public void setSistema(Sistema sistema)
   {
      this.sistema = sistema;
   }

   @Column(name = "tx_perfil", unique = true, length = 100)
   public String getTxPerfil()
   {
      return this.txPerfil;
   }

   public void setTxPerfil(String txPerfil)
   {
      this.txPerfil = txPerfil;
   }

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
   public Set<PerfilRotina> getPerfilRotinas()
   {
      return this.perfilRotinas;
   }

   public void setPerfilRotinas(Set<PerfilRotina> perfilRotinas)
   {
      this.perfilRotinas = perfilRotinas;
   }

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
   public Set<UsuarioPerfil> getUsuarioPerfils()
   {
      return this.usuarioPerfils;
   }

   public void setUsuarioPerfils(Set<UsuarioPerfil> usuarioPerfils)
   {
      this.usuarioPerfils = usuarioPerfils;
   }

}