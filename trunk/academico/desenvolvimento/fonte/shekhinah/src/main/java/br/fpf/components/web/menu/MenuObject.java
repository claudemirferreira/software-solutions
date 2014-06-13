package br.fpf.components.web.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuObject implements Serializable {

    /***/
    private static final long serialVersionUID = 1L;

    /**
     * Identificador do Menu.
     */
    private String id;

    /**
     * Servi�o ao qual est� associado o menu.
     */
    private String serviceUrl;

    /***/
    private MenuObject menuPai;

    /**
     * Lista de sub menus.
     */
    private List<MenuObject> subItems;

    private Boolean suspenso;

    /**
     * Construtor padr�o.
     */
    public MenuObject( MenuObject menuPai, String id, Boolean suspenso ) {
        this.id = id;
        this.menuPai = menuPai;
        this.subItems = new ArrayList<MenuObject>();
        this.suspenso = suspenso;
    }

    public String getId() {
        return this.id;
    }

    public String getServiceUrl() {
        return this.serviceUrl;
    }

    public void setServiceUrl( String serviceUrl ) {
        this.serviceUrl = serviceUrl;
    }

    public List<MenuObject> getSubItems() {
        return this.subItems;
    }

    public Boolean getSuspenso() {
        return this.suspenso;
    }

    public MenuObject getMenuPai() {
        return this.menuPai;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = ( prime * result ) + ( ( this.id == null ) ? 0 : this.id.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( this.getClass() != obj.getClass() ) {
            return false;
        }
        MenuObject other = (MenuObject) obj;
        if ( this.id == null ) {
            if ( other.id != null ) {
                return false;
            }
        } else if ( !this.id.equals( other.id ) ) {
            return false;
        }
        return true;
    }

}
