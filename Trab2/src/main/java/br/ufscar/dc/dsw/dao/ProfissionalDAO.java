package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface ProfissionalDAO extends CrudRepository<Profissional, Long>{

	Profissional findByCPF(String CPF);

	List<Profissional> findAll();

	List<Profissional> findByEspecialidade(String especialidade);
}
