package br.com.ss.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ss.data.entities.Membro;
import br.com.ss.data.repositories.MembroRepositorio;

/**
 * Conversor para entidade 'Membro'. Utilizado nos combos.
 * 
 * @author robson.ramos
 */
@FacesConverter(value = "membroConverter")
public class MembroConverter implements Converter {

	@Autowired
	private MembroRepositorio membroRepositorio;

	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String submittedValue ) {
		if (submittedValue.trim().equals("")) {  
            return null;  
        } 
    	int membroId = Integer.parseInt(submittedValue);  
    	
    	Membro membro = membroRepositorio.findOne(membroId);
    	
    	if (membro == null ) {
    		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Id do Membro não válido"));
    	}
    	
    	return membro;  
	}

	public String getAsString(FacesContext facesContext, UIComponent uicomp, Object entity) {
		return entity == null ? "-1" : ( (Membro) entity).getNome();
	}

}
