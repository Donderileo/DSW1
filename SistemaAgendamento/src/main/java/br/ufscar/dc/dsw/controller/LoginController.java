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
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/login/*")
public class LoginController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("login") != null) {
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
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		
		
		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
   
}