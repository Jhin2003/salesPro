package dao;

import db.ConnectionFactory;
import dto.CustomerDTO;
import utils.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CustomerDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customers");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String address = resultSet.getString("address");
                customers.add(new CustomerDTO(id, name, contact, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public CustomerDTO getCustomerByID(int ID) {

        try {
            String password = "miguel2003";
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            System.out.println(hashedPassword);
            preparedStatement = connection.prepareStatement("Select * FROM customers Where id = ?");
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id  = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String address = resultSet.getString("address");
                // Create and return a Customer object
                return  new CustomerDTO(id,name,contact,address);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addCustomer(CustomerDTO customer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customers (name, contact, address) VALUES (?, ?, ?)");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getContact());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(CustomerDTO customer) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE customers SET name = ?, contact = ?, address = ? WHERE id = ?");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getContact());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getId());
            preparedStatement.executeUpdate();
            System.out.println("updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE id = ?");
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


