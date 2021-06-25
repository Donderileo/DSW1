package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Collections;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;


@RestController
public class ProfissionalRestController {
	
	@Autowired
	private IUsuarioService service;

	@Autowired
	private IClienteService serviceCliente;
	
	@Autowired
	private IConsultaService serviceConsulta;
	
	@Autowired
	private IProfissionalService serviceProfissional;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Profissional profissional, JSONObject json) {
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				profissional.setId(((Integer) id).longValue());
			} else {
				profissional.setId((Long) id);
			}
		}

		profissional.setEmail((String) json.get("email"));
		profissional.setPassword((String) json.get("password"));
		profissional.setPapel((String) json.get("papel"));
		profissional.setCPF((String) json.get("cpf"));
		profissional.setName((String) json.get("name"));
		profissional.setEspecialidade((String) json.get("especialidade"));
		profissional.setCurriculo((String) json.get("curriculo"));

	}

	@PostMapping(path = "/profissionais")
	@ResponseBody
	public ResponseEntity<Profissional> cria(@RequestBody JSONObject json, BCryptPasswordEncoder encoder) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = new Profissional();
				parse(profissional, json);
				profissional.setPassword(encoder.encode(profissional.getPassword()));
				service.salvar(profissional);
				return ResponseEntity.ok(profissional);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@GetMapping(path = "/profissionais")
	public ResponseEntity<List<Profissional>> lista() {
		List<Profissional> lista = serviceProfissional.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/profissionais/{id}")
	public ResponseEntity<Profissional> listaPorId(@PathVariable("id") Long id) {
		Profissional profissional = serviceProfissional.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profissional);
	}
	
	@GetMapping(path = "/profissionais/especialidades/{esp}")
		public ResponseEntity<List<Profissional>> listaPorEspecialidade(@PathVariable("esp") String esp) {
			List<Profissional> profissionais = serviceProfissional.buscarPorEspecialidade(esp);
			if (profissionais == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(profissionais);
	}
	
	@PutMapping(path = "/profissionais/{id}")
	public ResponseEntity<Profissional> atualiza(@PathVariable("id") Long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = serviceProfissional.buscarPorId(id);
				if (profissional == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(profissional, json);
					service.salvar(profissional);
					return ResponseEntity.ok(profissional);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/profissionais/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") Long id) {
		Profissional profissional = serviceProfissional.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		} else if (!profissional.getConsultas().isEmpty()) {
			return ResponseEntity.ok(false);
		} else {
			service.excluir(id);
			return ResponseEntity.ok(true);
		}
	}
}
