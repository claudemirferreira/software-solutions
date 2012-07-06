package br.ss.authenticator.web.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

import br.ss.core.model.entity.AbstractEntity;

/**
 * Conversor para entidades. Utilizado nos combos.
 * Entidades devem herdar de {@link AbstractEntity}.
 * @author robson.ramos
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	private int index;

	@SuppressWarnings("unchecked")
	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String value ) {
		List<Object> items = new ArrayList<Object>();
		List<UIComponent> uicompList = uicomp.getChildren();
		for (UIComponent comp : uicompList) {
			if (comp instanceof UISelectItems) {
				items.addAll( ( List<SelectItem> ) ( ( UISelectItems ) comp).getValue());
			}
		}
		return "-1".equals(value) ? null : items.get(Integer.valueOf(value));
	}

	public String getAsString( FacesContext facesContext, UIComponent uicomp, Object entity ) {
		return entity == null ? "-1" : String.valueOf( index++ );
	}

}
