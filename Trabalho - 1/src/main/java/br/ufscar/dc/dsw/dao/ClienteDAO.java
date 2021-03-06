package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente (nome, email, senha, cpf, telefone, sexo, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            //statement.setString(7, cliente.getDataNasc());
            statement.setDate(7, java.sql.Date.valueOf(cliente.getDataNasc()));
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Cliente";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                
            	String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                //String dataNasc = resultSet.getString("dataNasc");
                LocalDate dataNasc = LocalDate.parse(resultSet.getDate("dataNasc").toString());
         
                Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);
                
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Cliente cliente) {
        String predelete = "DELETE FROM Consulta where cpfCliente = ?";
    	String sql = "DELETE FROM Cliente where cpf = ?";
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(predelete);

            statement.setString(1, cliente.getCpf());
            statement.executeUpdate();
            statement.close();
            
            
            statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCpf());
            statement.executeUpdate();
            statement.close();
            
            
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, email = ?, senha = ?, telefone = ?, sexo = ?, dataNasc = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
         
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getSexo());
            //statement.setString(6, cliente.getDataNasc());
            statement.setDate(6, java.sql.Date.valueOf(cliente.getDataNasc()));
            statement.setString(7, cliente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente getByCpf(String cpf) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                //String dataNasc = resultSet.getString("dataNasc");
                LocalDate dataNasc = LocalDate.parse(resultSet.getDate("dataNasc").toString());

                cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    
    public Cliente getByEmail(String email) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	
            	String nome = resultSet.getString("nome");
	            String senha = resultSet.getString("senha");
	            String cpf = resultSet.getString("cpf");
	            String telefone = resultSet.getString("telefone");
	            String sexo = resultSet.getString("sexo");
	            //String dataNasc = resultSet.getString("dataNasc");
	            LocalDate dataNasc = LocalDate.parse(resultSet.getDate("dataNasc").toString());

                 cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    
}