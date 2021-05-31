package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;


import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/consultas/*")
public class ConsultaController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ConsultaDAO dao;
    private ProfissionalDAO daoProfissional;

   
    @Override
    public void init() {
        dao = new ConsultaDAO();
        daoProfissional = new ProfissionalDAO();
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
            	case "/agendar":
            		agendar(request,response);
            		break;
            	case "/insere":
            		insereConsulta(request, response);
            		break;
            	case "/x":
            		RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/x.jsp");
            		dispatcher.forward(request, response);
            	
            	
            	
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
        
    }
	private void agendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
		String prof = request.getPathInfo();
		request.setAttribute("prof", prof);
		Erro erros = new Erro();
		if (clienteLogado == null) {
			erros.add("Precisa estar logado para acessar essa página.");
	        request.setAttribute("mensagens", erros);
	        String URL = "/login.jsp";
	        RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
	        return;
		}
		
		List<Profissional> listaProfissionais = daoProfissional.getAll();
		request.setAttribute("listaProfissionais",listaProfissionais);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/agendar.jsp");
		dispatcher.forward(request, response);
	}
	
private void insereConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	Erro erros = new Erro();
	Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
	
	if (clienteLogado == null) {
		erros.add("Precisa estar logado para acessar essa página.");

        request.setAttribute("mensagens", erros);
        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
	    rd.forward(request, response);
        return;
	}
	
	try {
        String cpfProfissional = request.getParameter("profissional");
        String dataInput = request.getParameter("data");
        String horario = request.getParameter("horario");
        
//        erros.add(cpfProfissional);
//        erros.add(dataInput);
//        erros.add(horario);
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date data = dateFormat.parse(dataInput + " " + horario + ":00");
        
        
        String cpfCliente = clienteLogado.getCpf();
        
        Consulta consulta = new Consulta(cpfCliente, cpfProfissional , data);
        

 //       erros.add(tms.toString());
        
//        Consulta verificaExistente = dao.get(cpfCliente, cpfProfissional, data);
        boolean existe = dao.getByDate(consulta);
       
        if (!existe) {
        	dao.insert(consulta);
        } else {
        	
        	erros.add("O horário escolhido já está ocupado por você ou pelo profissional.");
    		
    		request.setAttribute("mensagens", erros);
            String URL = "/consultas/x";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
     }
	} catch (Exception e) {
		System.out.print(e.toString());
		
		erros.add("Erro nos dados preenchidos.");
		
		request.setAttribute("mensagens", erros);
        String URL = "/consultas/x";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
	    rd.forward(request, response);
        return;
	}

	String URL = "/cliente/home.jsp"; 
	RequestDispatcher rd = request.getRequestDispatcher(URL);
	rd.forward(request, response);
}
	
	

   
}