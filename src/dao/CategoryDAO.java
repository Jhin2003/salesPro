package dao;

import db.ConnectionFactory;
import dto.CategoryDTO;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CategoryDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new CategoryDTO(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public CategoryDTO getCategoryByName(String NAME) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM categories Where name = ?");
            preparedStatement.setString(1, NAME);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               return new CategoryDTO(id,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
