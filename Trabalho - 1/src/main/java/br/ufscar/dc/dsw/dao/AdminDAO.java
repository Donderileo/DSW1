package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.domain.Admin;



public class AdminDAO extends GenericDAO {

    public Admin getByEmail(String email) {
        Admin admin = null;

        String sql = "SELECT * from Admin WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	
            	String nome = resultSet.getString("nome");
	            String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                 admin = new Admin(nome, email, senha, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    } 
    
}