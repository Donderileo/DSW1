package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

import java.io.IOException;

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
        String dataNasc = request.getParameter("dataNasc");
		
        Cliente cliente = new Cliente(cpf,nome,email,senha,telefone,sexo,dataNasc);
        dao.insert(cliente);
        
       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request,response);
	}

   
}