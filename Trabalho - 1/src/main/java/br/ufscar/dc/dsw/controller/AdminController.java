package br.ufscar.dc.dsw.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.util.Erro;


@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    
    private AdminDAO dao;
    
    private ProfissionalDAO daoProfissional;
    private ClienteDAO daoCliente;

    @Override
    public void init() {
        dao = new AdminDAO();
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }    	
    	
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
		
	    try {
	        switch (action) {
	            case "/listaCliente":
	                listaCliente(request, response);
	                break;
	            case "/listaProfissional":
	                listaProfissional(request, response);
	                break;
	        }
	    } catch (RuntimeException | IOException | ServletException e) {
	        throw new ServletException(e);
	    }
		
		request.getSession().invalidate();
		request.setAttribute("mensagens", erros);
	
		String URL = "/admin.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	
    }
    
    

    private void listaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = daoCliente.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/listaCliente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> listaProfissional = daoProfissional.getAll();
        request.setAttribute("listaProfissional", listaProfissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/listaProfissional.jsp");
        dispatcher.forward(request, response);
    }
    
    
   
}