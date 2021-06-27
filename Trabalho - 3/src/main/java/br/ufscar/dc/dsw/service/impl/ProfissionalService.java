package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	IProfissionalDAO dao;


	@Transactional(readOnly = true)
	public Profissional buscarPorId(long id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarPorEspecialidade(String especialidade) {
		return dao.findByEspecialidade(especialidade);
	}
	
	@Transactional(readOnly = true)
	public boolean profissionalTemConsulta(Long id) {
		return !dao.findById(id.longValue()).getConsultas().isEmpty(); 
	}
	
	@Transactional(readOnly = false)
	public void salvar(Profissional profissional) {
		dao.save(profissional);
	}
}
