package dao;

import db.ConnectionFactory;
import dto.CustomerDTO;
import dto.SalesrepDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesrepDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SalesrepDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public List<dto.SalesrepDTO> getAllSalesreps() {
        List<dto.SalesrepDTO> salesreps = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM salesreps");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String address = resultSet.getString("address");
                salesreps.add(new SalesrepDTO(id, name, contact, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesreps;
    }

    public dto.SalesrepDTO getSalesrepByID(int ID) {

        try {
            preparedStatement = connection.prepareStatement("Select * FROM salesreps Where id = ?");
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id  = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String address = resultSet.getString("address");
                // Create and return a Customer object
                return  new SalesrepDTO(id,name,contact,address);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addSalesrep(SalesrepDTO salesrep) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO salesreps (name, contact, address) VALUES (?, ?, ?)");
            preparedStatement.setString(1, salesrep.getName());
            preparedStatement.setString(2, salesrep.getContact());
            preparedStatement.setString(3, salesrep.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSalesrep(SalesrepDTO salesrep) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE salesreps SET name = ?, contact = ?, address = ? WHERE id = ?");
            preparedStatement.setString(1, salesrep.getName());
            preparedStatement.setString(2, salesrep.getContact());
            preparedStatement.setString(3, salesrep.getAddress());
            preparedStatement.setInt(4, salesrep.getId());
            preparedStatement.executeUpdate();
            System.out.println("updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSalesrep(int salesrepID) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM salesreps WHERE id = ?");
            preparedStatement.setInt(1, salesrepID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
