package br.com.ss.academico.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.repositorio.ResponsavelRepositorio;

/**
 * Conversor para entidade 'Responsavel'. Utilizado nos combos.
 * @author robson.ramos
 */
@FacesConverter(value = "responsavelConverter")
public class ResponsavelConverter implements Converter {

	// FIXME validar se precisa desse converter - deve usar o entityConverter
	
	@Autowired
	private ResponsavelRepositorio ResponsavelRepositorio;

	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String submittedValue ) {
		if (submittedValue.trim().equals("")) {  
            return null;  
        } 
    	Long ResponsavelId = Long.parseLong(submittedValue);  
    	
    	Responsavel Responsavel = ResponsavelRepositorio.findOne(ResponsavelId);
    	
    	if (Responsavel == null ) {
    		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Id do Responsavel não válido"));
    	}
    	
    	return Responsavel;  
	}

	public String getAsString(FacesContext facesContext, UIComponent uicomp, Object entity) {
		return entity == null ? "-1" : ( (Responsavel) entity).getNome();
	}

}
