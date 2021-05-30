package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/clientes/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
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
            		insereCliente(request, response);
            		break;
                case "/remover":
                    removeCliente(request, response);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/paginaCadastro.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insereCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        //String dataNasc = request.getParameter("dataNasc");
        LocalDate dataNasc = LocalDate.parse(request.getParameter("dataNasc"));
		
        Cliente cliente = new Cliente(cpf,nome,email,senha,telefone,sexo,dataNasc);
        dao.insert(cliente);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request,response);
	}
	
    private void removeCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        Cliente cliente = dao.getByCpf(cpf);
        dao.delete(cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
        dispatcher.forward(request, response);
    }
	    
    private void paginaEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        Cliente cliente = dao.getByCpf(cpf);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/paginaEdicao.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf");
        Cliente cliente = dao.getByCpf(cpf);

        String nome = request.getParameter("nome");
        if (nome == "") {
            nome = cliente.getNome();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = cliente.getEmail();
        }
        String senha = request.getParameter("senha");
        if (senha == "") {
            senha = cliente.getSenha();
        }
        String sexo = request.getParameter("sexo");
        if (sexo == "") {
        	sexo = cliente.getSexo();
        }

        String telefone = request.getParameter("telefone");
        if (telefone == "") {
            telefone = cliente.getTelefone();
        }
        
        LocalDate dataNasc = cliente.getDataNasc();
        try {
        	dataNasc = LocalDate.parse(request.getParameter("dataNasc"));
        }
        catch (Exception e) {
        	dataNasc = cliente.getDataNasc();
        }

        Cliente clienteNew = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);
        try {
            dao.update(clienteNew);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/cliente/paginaEdicao.jsp");
            rd.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
        dispatcher.forward(request, response);
    }
    
}