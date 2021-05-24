package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
                String data = resultSet.getString("data");


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

    public Consulta get(String cpfCliente, String cpfProfissional, String data) {
        Consulta consulta = null;
        
        String sql = "SELECT * from Consulta where cpfProfissional = ? and cpfCliente = ? and data = ?";
        
        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfCliente);
            statement.setString(2, cpfProfissional);
            statement.setString(3, data);

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

    public List<Consulta> getByCpf(String cpfCliente) {
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
                String data = resultSet.getString("data");

             

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
                String data = resultSet.getString("data");

               
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
    
    public void insert(Consulta Consulta){
        String sql = "INSERT INTO Consulta (cpfCliente, cpfProfissional, data) VALUES (?, ?, ?)";


        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, Consulta.getCpfCliente());
            statement.setString(2, Consulta.getCpfProfissional());
            statement.setString(3, Consulta.getData());
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            
        }
    }
}