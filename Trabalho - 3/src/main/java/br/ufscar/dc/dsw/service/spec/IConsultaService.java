package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Consulta;

public interface IConsultaService {

	Consulta buscarPorId(Long id);

	List<Consulta> buscarTodos();

	List<Consulta> buscarPorCliente(Cliente cliente);

	List<Consulta> buscarPorProfissional(Profissional profissional);

	void salvar(Consulta consulta);

	void excluir(Long id);
}
