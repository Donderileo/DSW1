package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.ConsultaClient;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.util.Erro;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import static br.ufscar.dc.dsw.domain.Constants.*;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ClienteDAO daoCli;
    private ConsultaDAO daoCon;
    private ProfissionalDAO dao;
    

    @Override
    public void init() {
        daoCli = new ClienteDAO();
        daoCon = new ConsultaDAO();  
        dao = new ProfissionalDAO();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
       
        try {
            switch (action) {
            	case "/cadastro":
            		paginaCadastro(request,response);
            		break;
            	case "/insere":
            		insereProfissional(request, response);
            		break;
                case "/remover":
                    removeProfissional(request, response);
                    break;
                case "/editar":
                    paginaEdicao(request, response);
                    break;	
                case "/atualizar":
                    atualizar(request, response);
                    break;	
                case "/listarConsultas":
              	  listarConsultas(request, response);
                    break;	
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
        
    }
    
 private void listarConsultas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Erro erros = new Erro();
    	
    	try {
	    	
    			Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");
		    	List<Consulta> listaConsulta = daoCon.getByCpfProfissional(profissional.getCpf());
				List<ConsultaClient> listaConsultaClient = new ArrayList<ConsultaClient>();
				
				Cliente cliente;
				
				for(Consulta consulta: listaConsulta) {
					cliente = daoCli.getByCpf(consulta.getCpfCliente());
					Timestamp tms = new Timestamp(consulta.getData().getTime());
					listaConsultaClient.add(new ConsultaClient(cliente, profissional, tms));
		        }
				
				
				request.getSession().setAttribute("listaConsulta", listaConsultaClient);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/listarConsultas.jsp");
				dispatcher.forward(request, response);
	    	
    	}
    	catch (Exception e) {
    		System.out.print(e.toString());
    		erros.add("Você precisa estar logado para acessar essa página");
    		request.setAttribute("mensagens", erros);
            String URL = "/consultas/x";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
    	    rd.forward(request, response);
            return;
    	}
	}
	private void paginaCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/paginaCadastro.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insereProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		
		request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String especialidade = request.getParameter("especialidade");
        String curriculo = request.getParameter("curriculo");
        
        
        
        Profissional profissional = new Profissional(cpf,nome,email,senha,especialidade,curriculo);
        try {
	        dao.insert(profissional);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	        dispatcher.forward(request,response);
        }
        catch(Exception e) {
        	erros.add("Informações em uso");
    		request.setAttribute("mensagens", erros);
            String URL = "/erros.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
    	    rd.forward(request, response);
            return;
        }
        
	}


	private void removeProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.getByCpf(cpf);
        dao.delete(profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/home.jsp");
        dispatcher.forward(request, response);
    }
	    
    private void paginaEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.getByCpf(cpf);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/paginaEdicao.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        
        Profissional profissional = dao.getByCpf(cpf);

        String nome = request.getParameter("nome");
        if (nome == "") {
            nome = profissional.getNome();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = profissional.getEmail();
        }
        String senha = request.getParameter("senha");
        if (senha == "") {
            senha = profissional.getSenha();
        }
        String especialidade = request.getParameter("especialidade");
        if (especialidade == "") {
        	especialidade = profissional.getEspecialidade();
        }
        String curriculo = request.getParameter("curriculo");
        if (curriculo == "") {
        	curriculo = profissional.getCurriculo();
        }

        Profissional profissionalNew = new Profissional(cpf, nome, email, senha, especialidade, curriculo);
        try {
            dao.update(profissionalNew);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/profissional/paginaEdicao.jsp");
            rd.forward(request, response);
        }

        
    }
	

}