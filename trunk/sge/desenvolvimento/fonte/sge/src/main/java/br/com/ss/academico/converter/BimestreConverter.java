package br.com.ss.academico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ss.academico.enumerated.Bimestre;

@FacesConverter(value = "bimestreConverter")
public class BimestreConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return Bimestre.getEnum(new Integer(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Bimestre sexo = Bimestre.getEnum(new Integer(value.toString()));
		if (sexo != null) {
			return sexo.getDescricao();
		}
		return null;
	}
}