package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public interface IProfissionalService {

	Profissional buscarPorId(long id);

	List<Profissional> buscarTodos();

	List<Profissional> buscarPorEspecialidade(String especialidade);

	boolean profissionalTemConsulta(Long id);
	
	void salvar(Profissional profissional);
}
