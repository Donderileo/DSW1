package br.ufscar.dc.dsw.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import br.ufscar.dc.dsw.domain.Consulta;


public class ConsultaDAO extends GenericDAO {

    public List<Consulta> getAll() {

        List<Consulta> listaConsulta = new ArrayList<>();

        String sql = "SELECT * from Consulta";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Convertendo resultados para a classe interna Consulta
            while (resultSet.next()) {
                String cpfProfissional = resultSet.getString("cpfProfissional");
                String cpfCliente = resultSet.getString("cpfCliente");
                Timestamp data = resultSet.getTimestamp("data");

                Date dataSql = new Date(data.getTime());
                Consulta consulta = new Consulta(cpfCliente, cpfProfissional, dataSql);
                listaConsulta.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsulta;
    }

    public Consulta get(String cpfCliente, String cpfProfissional, Date data) {
        Consulta consulta = null;
        
        String sql = "SELECT * from Consulta where cpfProfissional = ? and cpfCliente = ? and data = ?";
        
        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfCliente);
            statement.setString(2, cpfProfissional);
            statement.setTimestamp(3, new Timestamp(data.getTime()));

            ResultSet resultSet = statement.executeQuery();

            // Convertendo resultados para a classe interna Consulta
            if (resultSet.next()) {
                consulta = new Consulta(cpfCliente, cpfProfissional, data);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

    public List<Consulta> getByCpfCliente(String cpfCliente) {
        List<Consulta> listaConsulta = new ArrayList<>();
        
        String sql = "SELECT * from Consulta where cpfCliente = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfCliente);

            ResultSet resultSet = statement.executeQuery();

            // Convertendo resultados para a classe interna Consulta
            while (resultSet.next()) {
                String cpfProfissional = resultSet.getString("cpfProfissional");
                Date data = new Date(resultSet.getTimestamp("data").getTime());
             

                Consulta consulta = new Consulta(cpfCliente, cpfProfissional, data);
                listaConsulta.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsulta;
    }
    

    public List<Consulta> getByCpfProfissional(String cpfProfissional) {
        List<Consulta> listaConsulta = new ArrayList<>();
        
        String sql = "SELECT * from Consulta where cpfProfissional = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfProfissional);

            ResultSet resultSet = statement.executeQuery();

            // Convertendo resultados para a classe interna Consulta
            while (resultSet.next()) {
                String cpfCliente = resultSet.getString("cpfCliente");
                Date data = new Date(resultSet.getTimestamp("data").getTime());
                
                Consulta consulta = new Consulta(cpfCliente, cpfProfissional, data);
                listaConsulta.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsulta;
    }
    
    public boolean getByDate(Consulta consultaIn) {
    	List<Consulta> listaConsulta = new ArrayList<>();
    	String sql = "SELECT * from Consulta where data = ?";
    	
	   try {
		  
	       // Conectando no banco e realizando consulta
		   Connection conn = this.getConnection();
		   PreparedStatement statement = conn.prepareStatement(sql);
		   Timestamp tms = new Timestamp(consultaIn.getData().getTime());
		   statement.setString(1, tms.toString());
	
		   ResultSet resultSet = statement.executeQuery();
	
		   // Convertendo resultados para a classe interna Consulta
		   while (resultSet.next()) {
		       String cpfProfissional = resultSet.getString("cpfProfissional");
		       String cpfCliente = resultSet.getString("cpfCliente");
		       Date data_sql = resultSet.getDate("data");
		  

               Consulta consultaAux = new Consulta(cpfCliente, cpfProfissional, data_sql);
               listaConsulta.add(consultaAux);
	           }
   	
	           for(Consulta consulta: listaConsulta) {
	        	   if((consulta.getCpfCliente().equals(consultaIn.getCpfCliente())) || (consulta.getCpfProfissional().equals(consultaIn.getCpfProfissional()))) {
	        		   return true;
	        	   }
	           }
	           
	           resultSet.close();
	           statement.close();
	           conn.close();
	           
	           return false;
	           
	         
	       } catch (SQLException e) {
	           throw new RuntimeException(e);
	       }
		   
	       
	   }
    
    public void insert(Consulta consulta){
        String sql = "INSERT INTO Consulta (cpfCliente, cpfProfissional, data) VALUES (?, ?, ?)";


        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            Timestamp data = new Timestamp(consulta.getData().getTime());
            
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, consulta.getCpfCliente());
            statement.setString(2, consulta.getCpfProfissional());
            statement.setTimestamp(3, data);
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            
        }
    }
}