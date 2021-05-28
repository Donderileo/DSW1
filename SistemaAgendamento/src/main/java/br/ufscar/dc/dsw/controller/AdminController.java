package br.ufscar.dc.dsw.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.util.Erro;


@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    
    private AdminDAO dao;
    
    @Override
    public void init() {
       
        dao = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		
    	Erro erros = new Erro();
    	
		if (request.getParameter("loginAdmin") != null) {

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				AdminDAO dao = new AdminDAO();
				Admin admin = dao.getByEmail(email);
			
				if (admin != null) {
					if (admin.getSenha().equals(senha)) {
						request.getSession().setAttribute("adminLogado", admin);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/home.jsp");
						dispatcher.forward(request, response);
						return;
					} else {
						erros.add("Senha inválida!");
					}
				}	
				 else {
					erros.add("Usuário não encontrado!");
				} 
			}
			
		}
		
		request.getSession().invalidate();
		request.setAttribute("mensagens", erros);
	
		String URL = "/admin.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
   
}