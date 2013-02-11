package br.com.ss.centralaamar.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ss.centralaamar.domain.BatizadoDomain;
import br.com.ss.centralaamar.util.StringUtil;

/**
 * Conversor para valores do enum BatizadoDomain.
 * @see BatizadoDomain
 * @author robson.ramos
 */
@FacesConverter(value = "batizadoConverter")
public class BatizadoConverter implements Converter {

	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String value ) {
		if ( !StringUtil.notEmpty(value) ) {
			return null;
		}
		return new Boolean( value );
	}

	public String getAsString( FacesContext facesContext, UIComponent uicomp, Object value ) {
		if ( value == null ) {
			return null;
		}
		return BatizadoDomain.getEnum( new Short( value.toString() ) ).getDescricao();
	}

}
