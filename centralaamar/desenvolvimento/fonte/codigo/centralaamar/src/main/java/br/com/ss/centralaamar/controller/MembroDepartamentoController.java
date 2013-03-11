package br.com.ss.centralaamar.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.model.entity.Cargo;
import br.com.ss.centralaamar.model.entity.Departamento;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.MembroDepartamento;
import br.com.ss.centralaamar.service.ICargoService;
import br.com.ss.centralaamar.service.IDepartamentoService;
import br.com.ss.centralaamar.service.IMembroDepartamentoService;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class MembroDepartamentoController extends
		GenericBean<MembroDepartamento> {

	private static final long serialVersionUID = 6031730028318406591L;

	@Getter
	@Setter
	private Membro membro;

	@Getter
	@Setter
	private Cargo cargo;

	@Getter
	@Setter
	private Departamento departamento;

	@Getter
	@Setter
	private List<Membro> membros;

	@Getter
	@Setter
	private List<Cargo> cargos;

	@Getter
	@Setter
	private List<Departamento> departamentos;

	@Autowired
	private ICargoService cargoService;

	@Autowired
	private IDepartamentoService departamentoService;

	@Autowired
	private IMembroService membroService;

	@Autowired
	private IMembroDepartamentoService service;

	@Override
	protected IService<MembroDepartamento> getService() {
		return service;
	}

	public String save() throws SQLException {

		if (this.cargo.getIdCargo() != 0)
			this.entity.setCargo(this.cargo);

		if (this.departamento.getIdDepartamento() != 0)
			this.entity.setDepartamento(this.departamento);

		if (this.membro.getIdMembro() != 0)
			this.entity.setMembro(this.membro);

		return super.save();

	}

	public String cadastrar() throws InstantiationException,
			IllegalAccessException {

		this.cargos = cargoService.search(new Cargo());
		this.departamentos = departamentoService.search(new Departamento());
		this.membros = membroService.search(new Membro());

		this.cargo = new Cargo();
		this.departamento = new Departamento();
		this.membro = new Membro();

		return super.cadastrar();
	}

	public String editar(MembroDepartamento entity) {

		// this.setCargo(new Cargo());
		// this.setDepartamento(new Departamento());
		// this.setMembro(new Membro());

		if (null != entity.getCargo())
			this.cargo = entity.getCargo();
		if (entity.getDepartamento() != null)
			this.departamento = entity.getDepartamento();
		if (entity.getMembro() != null)
			this.membro = entity.getMembro();

		return super.editar(entity);

	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		cargos = cargoService.search(new Cargo());
		membros = membroService.search(new Membro());
		departamentos = departamentoService.search(new Departamento());

		cargo = new Cargo();
		departamento = new Departamento();
		membro = new Membro();

		super.setup();

	}

}