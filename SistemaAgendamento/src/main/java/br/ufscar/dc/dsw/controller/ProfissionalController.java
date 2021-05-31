package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ProfissionalDAO dao;

    @Override
    public void init() {
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
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
        
    }
	private void paginaCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/paginaCadastro.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insereProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String especialidade = request.getParameter("especialidade");
        String curriculo = request.getParameter("curriculo");
        
        Profissional profissional = new Profissional(cpf,nome,email,senha,especialidade,curriculo);
        dao.insert(profissional);
        
       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request,response);
	}

    private void removeProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.getByCpf(cpf);
        dao.delete(profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
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
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/profissional/paginaEdicao.jsp");
            rd.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
        dispatcher.forward(request, response);
    }
	
   
}