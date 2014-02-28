package br.com.ss.academico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ss.academico.enumerated.FatorRH;

@FacesConverter(value = "fatorRHConverter")
public class FatorRHConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return FatorRH.getEnum(new Integer(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		FatorRH sit = FatorRH.getEnum(new Integer(value.toString()));
		if (sit != null) {
			return sit.getDescricao();
		}
		return null;
	}

}
