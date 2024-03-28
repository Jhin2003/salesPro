package dao;

import db.ConnectionFactory;
import dto.CustomerDTO;
import dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
  public ProductDAO(){connection = ConnectionFactory.getConnection();}
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT p.code, p.name, p.description, p.price, p.stock_quantity, p.sold_quantity, p.reorder_point, c.name as category from products p JOIN categories c ON p.category_id = c.id;");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int code = resultSet.getInt("code");
                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int stockQuantity = resultSet.getInt("stock_quantity");
                int soldQuantity = resultSet.getInt("sold_quantity");
                int reorderPoint = resultSet.getInt("reorder_point");

               products.add(new ProductDTO(code,category,name,description, price, stockQuantity,soldQuantity,reorderPoint));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ProductDTO getProductByCode(int CODE) {

        try {
            preparedStatement = connection.prepareStatement("SELECT p.code, p.name, p.description, p.price, p.stock_quantity, p.sold_quantity, p.reorder_point, c.name as category from products p JOIN categories c ON p.category_id = c.id WHERE p.code = ?;");
            preparedStatement.setInt(1, CODE);
            resultSet = preparedStatement.executeQuery();
            if  (resultSet.next()) {
                int code = resultSet.getInt("code");
                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int stockQuantity = resultSet.getInt("stock_quantity");
                int soldQuantity = resultSet.getInt("sold_quantity");
                int reorderPoint = resultSet.getInt("reorder_point");

                return  new ProductDTO(code, category, name, description, price, stockQuantity,soldQuantity,reorderPoint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductDTO getProductByName(String NAME) {

        try {
            preparedStatement = connection.prepareStatement("SELECT p.code, p.name, p.description, p.price, p.stock_quantity, p.sold_quantity, p.reorder_point, c.name as category from products p JOIN categories c ON p.category_id = c.id WHERE p.name = ?;");
            preparedStatement.setString(1, NAME);
            resultSet = preparedStatement.executeQuery();
            if  (resultSet.next()) {
                int code = resultSet.getInt("code");
                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int stockQuantity = resultSet.getInt("stock_quantity");
                int soldQuantity = resultSet.getInt("sold_quantity");
                int reorderPoint = resultSet.getInt("reorder_point");

                return  new ProductDTO(code, category, name, description, price, stockQuantity,soldQuantity,reorderPoint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProduct(ProductDTO product) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO products (category_id, name, description, price, stock_quantity, sold_quantity, reorder_point) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1,product.getCategoryID());
            preparedStatement.setString(2,product.getName());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setInt(4,product.getPrice());
            preparedStatement.setInt(5,product.getStockQuantity());
            preparedStatement.setInt(6,product.getSoldQuantity());
            preparedStatement.setInt(7,product.getReorderPoint());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteProduct(int code) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM products WHERE code = ?");
            preparedStatement.setInt(1, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(ProductDTO product) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET category_id = ?, name = ?, description = ?, price = ?, stock_quantity = ?, sold_quantity = ?, reorder_point = ? WHERE code = ?");
            System.out.println("helloooooooooooo");
           preparedStatement.setInt(1, product.getCategoryID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setInt(5, product.getStockQuantity());
            preparedStatement.setInt(6, product.getSoldQuantity());
            preparedStatement.setInt(7, product.getReorderPoint());
            preparedStatement.setInt(8, product.getCode());
            preparedStatement.executeUpdate();
            System.out.println("updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
