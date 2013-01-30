package br.com.ss.centralaamar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.component.Batizado;
import br.com.ss.centralaamar.component.MembroIgreja;
import br.com.ss.centralaamar.component.ModoConversao;
import br.com.ss.centralaamar.component.Sexo;
import br.com.ss.centralaamar.component.TemFilho;
import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Profissao;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.IPastorService;
import br.com.ss.centralaamar.service.IPequenoGrupoService;
import br.com.ss.centralaamar.service.IProfissaoService;
import br.com.ss.centralaamar.service.IService;

@Controller
@ManagedBean(name="membroController")
@Named
@Scope("session")
public class MembroController extends GenericBean<Membro> {

	@Autowired
	private IMembroService service;
	@Autowired
	private IProfissaoService profissaoService;
	@Autowired
	private IPastorService pastorService;
	@Autowired
	private IPequenoGrupoService pequenoGrupoService;

	@Getter	@Setter
	private List<Batizado> batizados = Batizado.list();

	@Getter	@Setter
	private List<MembroIgreja> membroIgrejas = MembroIgreja.list();

	@Getter	@Setter
	private List<ModoConversao> modoConvecaos = ModoConversao.list();

	@Getter	@Setter
	private List<Sexo> sexos = Sexo.list();

	@Getter	@Setter
	private List<TemFilho> temFilhos = TemFilho.list();

	@Getter	@Setter
	private Pastor pastor = new Pastor();

	@Getter	@Setter
	private Profissao profissao = new Profissao();

	@Getter	@Setter
	private PequenoGrupo pequenoGrupo = new PequenoGrupo();

	@Getter	@Setter
	private List<Pastor> pastors = new ArrayList<Pastor>();

	@Getter	@Setter
	private List<Profissao> profissaos = new ArrayList<Profissao>();

	@Getter	@Setter
	private List<PequenoGrupo> pequenoGrupos = new ArrayList<PequenoGrupo>();

	@Override
	protected IService<Membro> getService() {
		return service;
	}

	public String save() {
		try {
			this.entity.setNome(this.entity.getNome().toUpperCase());
			return super.save();
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnning", e
							.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e
							.getMessage()));
		}

		return null;

	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		pastors = pastorService.search(new Pastor());
		profissaos = profissaoService.search(new Profissao());
		pequenoGrupos = pequenoGrupoService.search(new PequenoGrupo());

		profissao = new Profissao();
		pastor = new Pastor();
		pequenoGrupo = new PequenoGrupo();

		super.setup();

	}
}