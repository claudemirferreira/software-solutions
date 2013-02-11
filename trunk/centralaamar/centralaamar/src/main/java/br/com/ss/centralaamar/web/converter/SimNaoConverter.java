package br.com.ss.centralaamar.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ss.centralaamar.domain.SimNaoDomain;

/**
 * Conversor para valores booleano: Sim e Não. Utilizado nos combos.
 * @author robson.ramos
 */
@FacesConverter(value = "simNaoConverter")
public class SimNaoConverter implements Converter {

	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String value ) {
		if ( !( Boolean.FALSE.toString().equals( value)
				|| Boolean.TRUE.toString().equals( value) ) ) {
			return null;
		}
		return new Boolean( value );
	}

	public String getAsString( FacesContext facesContext, UIComponent uicomp, Object value ) {
		if ( value == null ) {
			return null;
		}
		return SimNaoDomain.getEnumByValue( new Boolean( value.toString() ) ).getDescricao();
	}
}
