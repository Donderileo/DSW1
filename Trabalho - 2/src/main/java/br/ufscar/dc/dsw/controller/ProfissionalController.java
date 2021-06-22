package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private IProfissionalService profissionalService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String esp, ModelMap model) {
		List<Profissional> profissionais = profissionalService.buscarTodos();
		Set<String> especialidades = new HashSet<String>();

		for (Profissional profissional : profissionais) {
			
			String especialidade = profissional.getEspecialidade();
            if (!especialidades.contains(especialidade)) {
            	especialidades.add(especialidade);
            }
            
		}

		if (esp != null) {
			profissionais = profissionalService.buscarPorEspecialidade(esp);
		}

		model.addAttribute("profissionais", profissionais);
		model.addAttribute("especialidades", especialidades);
		return "profissional/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) throws IOException {
		if (profissional.getPapel() == null) {
			profissional.setPapel("Pro");
		}
		
		 if (result.hasErrors()) {
			File arquivo = new File( "/home/donderi/erro.txt" );
			boolean existe = arquivo.exists();
			
			arquivo.createNewFile();
			FileWriter fw = new FileWriter( arquivo, true );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(result.toString());
			bw.close();
			fw.close();
			
		 	return "/profissional/cadastro";
		 }
		
		profissional.setPassword(encoder.encode(profissional.getPassword()));
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional cadastrado");
		return "redirect:/profissionais/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", usuarioService.buscarPorId(id));
		return "profissional/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		
		if (profissional.getPapel() == null) {
			profissional.setPapel("Pro");
		}

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		usuarioService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional alterado");
		return "redirect:/profissionais/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id,  RedirectAttributes attr) {
		if (profissionalService.profissionalTemConsulta(id)) {
			attr.addFlashAttribute("fail", "Profissional possui consultas, não pode ser excluido");
		}
		else {
			usuarioService.excluir(id);
			attr.addFlashAttribute("sucess", "Profissional excluído");
		}
		return "redirect:/profissionais/listar";
	}
}
