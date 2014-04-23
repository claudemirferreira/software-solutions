package br.com.ss.academico.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.repositorio.ResponsavelRepositorio;
import br.com.ss.academico.repositorio.ResponsavelRepositorioJPA;
import br.com.ss.academico.servico.ResponsavelServico;

/**
 * Conversor para entidade 'Responsavel'. Utilizado nos combos.
 * @author robson.ramos
 */
@FacesConverter(value = "responsavelConverter")
public class ResponsavelConverter implements Converter {

	// FIXME validar se precisa desse converter - deve usar o entityConverter
	
//	@Autowired
//	private ResponsavelRepositorioJPA repositorio;

	@ManagedProperty(value = "#{responsavelServicoImpl}")
	private ResponsavelServico responsavelServico;

	public Object getAsObject( FacesContext facesContext, UIComponent uicomp, String submittedValue ) {
		if (submittedValue.trim().equals("")) {  
            return null;  
        } 
//    	Long ResponsavelId = Long.parseLong(submittedValue);  
    	// FIXME injetar repositorio..
    	Responsavel Responsavel = responsavelServico.findByNome(submittedValue);
    	
    	if (Responsavel == null ) {
    		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Id do Responsavel não válido"));
    	}
    	
    	return Responsavel;  
	}

	public String getAsString(FacesContext facesContext, UIComponent uicomp, Object entity) {
		return entity == null ? "-1" : ( (Responsavel) entity).getNome();
	}


	public ResponsavelServico getResponsavelServico() {
		return responsavelServico;
	}

	public void setResponsavelServico(ResponsavelServico responsavelServico) {
		this.responsavelServico = responsavelServico;
	}

}
