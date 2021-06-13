package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Consulta;

@SuppressWarnings("unchecked")
public interface ConsultaDAO extends CrudRepository<Consulta, Long>{

	List<Consulta> findAll();
	
    List<Consulta> findByCliente(Cliente cliente);
	
    List<Consulta> findByProfissional(Profissional profissional);

}
