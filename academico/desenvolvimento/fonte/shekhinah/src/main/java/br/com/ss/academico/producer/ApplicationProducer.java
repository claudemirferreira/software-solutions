package br.com.ss.academico.producer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.com.ss.academico.enumerated.NaoSim;
import br.com.ss.academico.enumerated.Situacao;
import br.com.ss.academico.enumerated.StatusMatricula;

@ApplicationScoped
public class ApplicationProducer {
	
	@Produces @Named @ApplicationScoped
	public List<SelectItem> ativoInativoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (Situacao c : Situacao.values()) {
			list.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		return list;
	}

	@Produces @Named @ApplicationScoped
	public List<SelectItem> naoSimList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (NaoSim c : NaoSim.values()) {
			list.add(new SelectItem(c.getValue(), c.getDescricao()));
		}
		return list;
	}

	@Produces @Named @ApplicationScoped
	public List<SelectItem> statusMatriculaList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (StatusMatricula c : StatusMatricula.values()) {
			list.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		return list;
	}
	
	
}
