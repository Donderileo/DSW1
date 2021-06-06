package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface ClienteDAO extends CrudRepository<Cliente, Long>{

	Cliente findByCPF(String CPF);
	
	List<Cliente> findAll();
	
}
