package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalService {

	Profissional buscarPorId(long id);

	List<Profissional> buscarTodos();

	List<Profissional> buscarPorEspecialidade(String especialidade);

	boolean profissionalTemConsulta(Long id);
}
