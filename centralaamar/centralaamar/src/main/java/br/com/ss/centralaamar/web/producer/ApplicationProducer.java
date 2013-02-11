package br.com.ss.centralaamar.web.producer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.com.ss.centralaamar.domain.AtivoInativoDomain;
import br.com.ss.centralaamar.domain.BatizadoDomain;
import br.com.ss.centralaamar.domain.MasculinoFemininoDomain;
import br.com.ss.centralaamar.domain.ModoConversaoDomain;
import br.com.ss.centralaamar.domain.SimNaoDomain;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;

@ApplicationScoped
public class ApplicationProducer {
	
	@Produces @Named @ApplicationScoped
	public List<SelectItem> ativoInativoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (AtivoInativoDomain c : AtivoInativoDomain.values()) {
			list.add(new SelectItem(c.getValue(), c.getDescricao()));
		}
		return list;
	}

	@Produces @Named @ApplicationScoped
	public List<SelectItem> simNaoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (SimNaoDomain c : SimNaoDomain.values()) {
			list.add(new SelectItem(c.isValue(), c.getDescricao()));
		}
		return list;
	}

	@Produces @Named @ApplicationScoped
	public List<SelectItem> modoConversaoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (ModoConversaoDomain c : ModoConversaoDomain.values()) {
			list.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		return list;
	}

	@Produces @Named @ApplicationScoped
	public List<SelectItem> masculinoFemininoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (MasculinoFemininoDomain c : MasculinoFemininoDomain.values()) {
			list.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		return list;
	}

	@Produces @Named @ApplicationScoped
	public List<SelectItem> batizadoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (BatizadoDomain c : BatizadoDomain.values()) {
			list.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		return list;
	}
	
	
}
