package br.com.ss.centralaamar.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.IPequenoGrupoService;
import br.com.ss.centralaamar.service.IService;

@Component("pequenoGrupoController")
@Named
@Scope("session")
public class PequenoGrupoController extends GenericBean<PequenoGrupo> {

	private static final long serialVersionUID = 5221608581326985775L;

	@Autowired
	private IPequenoGrupoService service;

	@Autowired
	private IMembroService membroService;

	@Getter
	@Setter
	private Membro anfitriao;

	@Getter
	@Setter
	private Membro coordenador;

	@Getter
	@Setter
	private List<Membro> anfitriaos;

	@Getter
	@Setter
	private List<Membro> coordenadors;

	@Override
	protected IService<PequenoGrupo> getService() {
		return service;
	}

	@Override
	public String save() {
		entity.setNome(this.entity.getNome().toUpperCase());
		if (this.getAnfitriao() != null)
			entity.setAnfitriao(this.anfitriao);
		if (entity.getCoordenador() != null)
			this.coordenador = entity.getCoordenador();

		try {
			return super.save();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		anfitriaos = membroService.search(new Membro());
		coordenadors = anfitriaos;
		anfitriao = new Membro();
		coordenador = new Membro();

		super.setup();
	}

	public String editar(PequenoGrupo entity) {
//		this.anfitriao = new Membro();
//		this.coordenador = new Membro();

		if (this.anfitriao != null)
			entity.setAnfitriao(this.anfitriao);
		if (this.coordenador != null)
			entity.setCoordenador(this.coordenador);

		return super.editar(entity);

	}

	@SuppressWarnings("unchecked")
	public void print() {
		this.relatorio.setPath("D:\\jasper\\pequenoGrupo.jasper");
		this.relatorio.setResultList(this.resultList);
		super.print();
	}

}