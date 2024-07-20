package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.BCrypt;

public class AdminLogin {

    private Connection connection;

    public AdminLogin() {
        connection = ConnectionFactory.getConnection();
    }

    public void addAdmin (String username, String password){
        try {
            String sql = "Insert into admins(name,password) values(?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean IsPasswordValidated(String username, String password) {
        try {
            String sql = "SELECT password FROM admins WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                if (password.equals(storedHashedPassword)){
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}