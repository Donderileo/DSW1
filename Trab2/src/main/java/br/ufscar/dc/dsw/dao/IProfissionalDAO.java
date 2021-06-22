package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long>{

	
	Profissional findById(long id);

	List<Profissional> findAll();

	List<Profissional> findByEspecialidade(String especialidade);
	
	Profissional save(Profissional profissional);
}
