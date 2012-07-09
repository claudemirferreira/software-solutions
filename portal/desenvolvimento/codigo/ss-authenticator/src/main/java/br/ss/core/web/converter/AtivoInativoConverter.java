package br.ss.core.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ss.core.constant.AtivoInativoConstant;

@FacesConverter("ativoInativoConverter")
public class AtivoInativoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return new Boolean( value );
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return AtivoInativoConstant.findByValue( new Boolean( value.toString() ) ).getDescricao();
	}

}
