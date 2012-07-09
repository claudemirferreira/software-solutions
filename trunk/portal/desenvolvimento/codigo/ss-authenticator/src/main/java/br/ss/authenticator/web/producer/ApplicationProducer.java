package br.ss.authenticator.web.producer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.ss.core.constant.AtivoInativoConstant;

@ApplicationScoped
public class ApplicationProducer {
	
	
	
	
	@Produces @Named @ApplicationScoped
	public List<SelectItem> ativoInativoList() {
		List<SelectItem> ativoInativoList = new ArrayList<SelectItem>();
		for (AtivoInativoConstant c : AtivoInativoConstant.values()) {
			ativoInativoList.add(new SelectItem(c.getValue(), c.getDescricao()));
		}
		return ativoInativoList;
	}
	

}
