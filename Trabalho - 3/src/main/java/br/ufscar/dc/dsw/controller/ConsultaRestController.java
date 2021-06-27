package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@RestController
public class ConsultaRestController {
	@Autowired
	private IClienteService serviceCliente;
	
	@Autowired
	private IConsultaService serviceConsulta;
	
	@Autowired
	private IProfissionalService serviceProfissional;
	
	@GetMapping(path = "/consultas")
	public ResponseEntity<List<Consulta>> lista() {
		List<Consulta> lista = serviceConsulta.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> listaPorId(@PathVariable("id") Long id) {
		Consulta consulta = serviceConsulta.buscarPorId(id);
		if (consulta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}
	
	@GetMapping(path = "/consultas/clientes/{id}")
	public ResponseEntity<List<Consulta>> listaPorIdCliente(@PathVariable("id") Long id) {
		Cliente cliente = serviceCliente.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		List<Consulta> lista = serviceConsulta.buscarPorCliente(cliente);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/consultas/profissionais/{id}")
	public ResponseEntity<List<Consulta>> listaPorIdProfissional(@PathVariable("id") Long id) {
		Profissional profissional = serviceProfissional.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		}
		List<Consulta> lista = serviceConsulta.buscarPorProfissional(profissional);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
}
