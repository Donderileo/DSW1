package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



import br.ufscar.dc.dsw.domain.Pessoa;

@SuppressWarnings("unchecked")
public interface PessoaDAO extends CrudRepository<Pessoa, Long>{
	
	@Query("SELECT p FROM Pessoa p WHERE p.email = :email")
    public Pessoa getUserByEmail(@Param("email") String email);

	Pessoa findByEmail(String email);

	List<Pessoa> findAll();
	
}
