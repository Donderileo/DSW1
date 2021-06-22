package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional){
        String sql = "INSERT INTO Profissional (cpf, nome, email, senha, especialidade, curriculo) VALUES(?, ?, ?, ?, ?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getNome());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getSenha());
            statement.setString(5, profissional.getEspecialidade());
            statement.setString(6, profissional.getCurriculo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
        	throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissional = new ArrayList<>();

        String sql = "SELECT * from Profissional";

        try {
            
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String especialidade = resultSet.getString("especialidade");
                String curriculo = resultSet.getString("curriculo");
                Profissional profissional = new Profissional(cpf, nome, email, senha, especialidade, curriculo);
                listaProfissional.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissional;
    }

    public void delete(Profissional profissional){
    	String predelete = "DELETE FROM Consulta where cpfProfissional = ?";
     	String sql = "DELETE FROM Profissional where cpf = ?";
         
         try {
             Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(predelete);

             statement.setString(1, profissional.getCpf());
             statement.executeUpdate();
             statement.close();
             
             
             statement = conn.prepareStatement(sql);

             statement.setString(1, profissional.getCpf());
             statement.executeUpdate();
             statement.close();
             
             
             conn.close();
         } catch (SQLException e) {
         }
    }

    public void update(Profissional profissional){
        String sql = "UPDATE Profissional SET nome = ?, email = ?, senha = ?, especialidade = ?, curriculo = ? WHERE cpf = ?";
        
        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setString(4, profissional.getEspecialidade());
            statement.setString(5, profissional.getCurriculo());
            statement.setString(6, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Profissional getByCpf(String cpf) {
        Profissional profissional = null;
        
        String sql = "SELECT * from Profissional where cpf = ?";

        try {
            
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String especialidade = resultSet.getString("especialidade");
                String curriculo = resultSet.getString("curriculo");
                profissional = new Profissional(cpf, nome, email, senha, especialidade, curriculo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

     public Profissional getByEmail(String email) {
        Profissional profissional = null;
        String sql = "SELECT * from Profissional WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String especialidade = resultSet.getString("especialidade");
                String curriculo = resultSet.getString("curriculo");
                profissional = new Profissional(cpf, nome, email, senha, especialidade, curriculo);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
    public List<Profissional> getAllEspecialidade(String especialidade) {
        List<Profissional> listaProfissional = new ArrayList<>();

        String sql = "SELECT * from Profissional where especialidade = ?";

        try {
            
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, especialidade);
            ResultSet resultSet = statement.executeQuery();

            
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String curriculo = resultSet.getString("curriculo");
                Profissional profissional = new Profissional(cpf, nome, email, senha, especialidade, curriculo);
                listaProfissional.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissional;
    }
}