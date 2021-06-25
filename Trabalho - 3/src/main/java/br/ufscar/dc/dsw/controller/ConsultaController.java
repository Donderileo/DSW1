package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
	@Autowired
	private IConsultaService consultaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IProfissionalService profissionalService;
	
	private Cliente getClienteAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return clienteService.buscarPorId(user.getId());
	}
	
	private Profissional getProfissionalAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return profissionalService.buscarPorId(user.getId());
	}
	
	private boolean verificaDataHoraOcupada(Consulta consulta) {
		List<Consulta> consultas = consultaService.buscarPorProfissional(consulta.getProfissional());
		
		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).getDataConsulta().equals(consulta.getDataConsulta())
					&& consultas.get(i).getHoraConsulta() == consulta.getHoraConsulta()) {
				return true;
			}
		}
		
		consultas = consultaService.buscarPorCliente(consulta.getCliente());
		
		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).getDataConsulta().equals(consulta.getDataConsulta())
					&& consultas.get(i).getHoraConsulta() == consulta.getHoraConsulta()) {
				return true;
			}
		}
		
		return false;
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Consulta consulta, ModelMap model) {
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "consulta/cadastro";
	}

	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		List<Consulta> consultas = consultaService.buscarPorCliente(getClienteAutenticado());
		
		if(consultas.isEmpty()) {
			consultas = consultaService.buscarPorProfissional(getProfissionalAutenticado());
		}
		
		model.addAttribute("consultas", consultas);
		return "consulta/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("profissionais", profissionalService.buscarTodos());
			return "consulta/cadastro";
		}
		
		if (verificaDataHoraOcupada(consulta)) {
			model.addAttribute("profissionais", profissionalService.buscarTodos());
			attr.addFlashAttribute("fail", "Consulta não marcada. Horário indisponível.");
			return "redirect:/consultas/cadastrar";
		}
		
		consulta.setCliente(getClienteAutenticado());
		consultaService.salvar(consulta);
		
		attr.addFlashAttribute("sucess", "Consulta marcada.");
		return "redirect:/consultas/listar";
		
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		model.addAttribute("consulta", consultaService.buscarPorId(id));
		return "consulta/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("profissionais", profissionalService.buscarTodos());
			return "consulta/cadastro";
		}
		

		consulta.setCliente(getClienteAutenticado());
		consultaService.salvar(consulta);
		
		attr.addFlashAttribute("sucess", "Consulta editada com sucesso");
		return "redirect:/consultas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		consultaService.excluir(id);
		
		return "redirect:/consultas/listar";
	}
}
