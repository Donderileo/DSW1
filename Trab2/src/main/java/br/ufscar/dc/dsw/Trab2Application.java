package br.ufscar.dc.dsw;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.domain.*;

@SpringBootApplication
public class Trab2Application {

	private static final Logger log = LoggerFactory.getLogger(Trab2Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Trab2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IProfissionalDAO profissionalDAO, IConsultaDAO consultaDAO, IClienteDAO clienteDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
		
			Usuario user = new Usuario();
			user.setEmail("admin@admin.com");
			user.setPassword(encoder.encode("admin"));
			user.setCPF("105");
			user.setName("admin");
			user.setPapel("Adm");
			usuarioDAO.save(user);
			
			
			Profissional profissional1 = new Profissional();
			profissional1.setEmail("bruno@gmail.com");
			profissional1.setPassword(encoder.encode("bruno"));
			profissional1.setName("bruno");
			profissional1.setCPF("123");
			profissional1.setPapel("Pro");
			profissional1.setEspecialidade("Dentista");
			profissional1.setCurriculo("xulaaaa");

			log.info("Salvando Profissional bruno");

			profissionalDAO.save(profissional1);

			Profissional profissional2 = new Profissional();
			profissional2.setEmail("bruna@gmail.com");
			profissional2.setPassword(encoder.encode("bruna"));
			profissional2.setName("bruna");
			profissional2.setCPF("0123");
			profissional2.setPapel("Pro");
			profissional2.setEspecialidade("Ottorinolaringologista");
			profissional2.setCurriculo("yulaaaa");

			log.info("Salvando Profissional bruna");

			profissionalDAO.save(profissional2);


			Cliente cliente1 = new Cliente();
			cliente1.setEmail("marcos@gmail.com");
			cliente1.setPassword(encoder.encode("marcos"));
			cliente1.setName("marcos");
			cliente1.setCPF("1234");
			cliente1.setPapel("Cli");
			cliente1.setTelefone("19-123");
			cliente1.setSexo("Masculino");
			cliente1.setDataNasc("12/03/2015");

			log.info("Salvando marcos");

			clienteDAO.save(cliente1);

			Cliente cliente2 = new Cliente();
			cliente2.setEmail("carlos@gmail.com");
			cliente2.setPassword(encoder.encode("carlos"));
			cliente2.setName("carlos");
			cliente2.setCPF("12345");
			cliente2.setPapel("Cli");
			cliente2.setTelefone("19-123-4");
			cliente2.setSexo("Masculino");
			cliente2.setDataNasc("12/01/2012");
			log.info("Salvando cliente2 2");

			clienteDAO.save(cliente2);
			
			Consulta consulta1 = new Consulta();
			consulta1.setCliente(cliente1);
			consulta1.setProfissional(profissional1);
			consulta1.setDataConsulta("2021-05-21");
			consulta1.setHoraConsulta(15);

			log.info("Salvando Consulta1 1-1");
			consultaDAO.save(consulta1);


			Consulta consulta2 = new Consulta();
			consulta2.setCliente(cliente2);
			consulta2.setProfissional(profissional2);
			consulta2.setDataConsulta("2021-05-21");
			consulta2.setHoraConsulta(15);

			log.info("Salvando Consulta2 2-2");
			consultaDAO.save(consulta2);


			Consulta consulta3 = new Consulta();
			consulta3.setCliente(cliente1);
			consulta3.setProfissional(profissional2);
			consulta3.setDataConsulta("2021-05-22");
			consulta3.setHoraConsulta(15);

			log.info("Salvando Consulta3 1-2 - deve dar erro");
			consultaDAO.save(consulta3);

			List<Usuario> usuarios = usuarioDAO.findAll();

			log.info("Imprimindo usuarios - findAll()");

			for (Usuario u : usuarios) {
				log.info(u.getName().toString());
			}
			
			List<Cliente> clientes = clienteDAO.findAll();

			log.info("Imprimindo clientes - findAll()");

			for (Cliente c : clientes) {
				log.info(c.getName().toString());
			}
			
			List<Profissional> profissionais = profissionalDAO.findAll();

			log.info("Imprimindo profissional - findAll()");

			for (Profissional p : profissionais) {
				log.info(p.getName().toString());
			}	
		};
	}
}