package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;

import br.ufscar.dc.dsw.domain.ConsultaClient;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/login/*")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ClienteDAO daoCli;
    private ProfissionalDAO daoPro;
    private ConsultaDAO daoCon;
    
    @Override
    public void init() {
        daoCli = new ClienteDAO();
        daoPro = new ProfissionalDAO();
        daoCon = new ConsultaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("loginCliente") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				ClienteDAO dao = new ClienteDAO();
				Cliente cliente = dao.getByEmail(email);
				
				if (cliente != null) {
					if (cliente.getSenha().equals(senha)) {
						
						List<Consulta> listaConsulta = daoCon.getByCpfCliente(cliente.getCpf());
						List<ConsultaClient> listaConsultaClient = new ArrayList<ConsultaClient>();
						
						Profissional profissional;
						
						for(Consulta consulta: listaConsulta) {
							profissional = daoPro.getByCpf(consulta.getCpfProfissional());
							Timestamp tms = new Timestamp(consulta.getData().getTime());
							listaConsultaClient.add(new ConsultaClient(cliente, profissional, tms));
				        }
						
						
						request.getSession().setAttribute("listaConsulta", listaConsultaClient);
						request.getSession().setAttribute("clienteLogado", cliente);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/home.jsp");
						dispatcher.forward(request, response);
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		else if(request.getParameter("loginProfissional") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				
				Profissional profissional = daoPro.getByEmail(email);
				
				if (profissional != null) {
					if (profissional.getSenha().equals(senha)) {
						
						List<Consulta> listaConsulta = daoCon.getByCpfProfissional(profissional.getCpf());
						List<ConsultaClient> listaConsultaClient = new ArrayList<ConsultaClient>();
						
						Cliente cliente;
						
						for(Consulta consulta: listaConsulta) {
							cliente = daoCli.getByCpf(consulta.getCpfCliente());
							Timestamp tms = new Timestamp(consulta.getData().getTime());
							listaConsultaClient.add(new ConsultaClient(cliente, profissional, tms));
				        }
						
						
						request.getSession().setAttribute("listaConsulta", listaConsultaClient);
						request.getSession().setAttribute("profissionalLogado", profissional);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/home.jsp");
						dispatcher.forward(request, response);
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		
		
		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
   
}