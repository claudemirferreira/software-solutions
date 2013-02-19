package br.com.ss.portal.service;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Rotina;

public interface IRotinaService extends IService<Rotina> {

	public List<Rotina> searchRotinasDisponivel(Perfil entity);
}