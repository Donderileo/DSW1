package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public interface IClienteService {
	List<Cliente> buscarTodos();


	Cliente buscarPorId(long id);

	boolean clienteTemConsulta(Long id);
}
