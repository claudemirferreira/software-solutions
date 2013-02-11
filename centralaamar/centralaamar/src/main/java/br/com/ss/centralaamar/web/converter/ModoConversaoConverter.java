package br.com.ss.centralaamar.web.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

import br.com.ss.centralaamar.domain.ModoConversaoDomain;
import br.com.ss.centralaamar.util.StringUtil;

/**
 * Conversor para valores do enum ModoConversao.
 * @see ModoConversaoDomain
 * @author robson.ramos
 */
@FacesConverter(value = "modoConversaoConverter")
public class ModoConversaoConverter implements Converter {

	private int index;

	@SuppressWarnings("unchecked")
	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String value ) {
		/* => o item 0 no combo é nulo (o "Selecione um registro" ou "Todos os registros" ) */
		if ( !StringUtil.notEmpty(value) || value.equals( ( ( Integer ) 0 ).toString() ) ) {
			return null;
		}
		List<Object> items = new ArrayList<Object>();
		List<UIComponent> uicompList = uicomp.getChildren();
		for (UIComponent comp : uicompList) {
			if (comp instanceof UISelectItems) {
				items.addAll( ( List<SelectItem> ) ( ( UISelectItems ) comp).getValue());
			}
		}
		/* -1 : para nao considerar o item 0 do combo */ 
		return "-1".equals(value) ? null : items.get(Integer.valueOf(value) -1); 
	}

	public String getAsString( FacesContext facesContext, UIComponent uicomp, Object value ) {
		if ( value == null ) {
			return null;
		}
		return ModoConversaoDomain.getEnum( new Short( value.toString() ) ).getDescricao();
//		return entity == null ? "-1" : String.valueOf( index++ );
	}

}
