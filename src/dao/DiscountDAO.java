package dao;

import db.ConnectionFactory;
import dto.CategoryDTO;
import dto.CustomerDTO;
import dto.DiscountDTO;
import dto.SalesrepDTO;
import utils.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
    public class DiscountDAO {

        private Connection connection;
        private PreparedStatement preparedStatement;
        private ResultSet resultSet;

        public DiscountDAO() {
            connection = ConnectionFactory.getConnection();
        }

        public List<DiscountDTO> getAllDiscounts() {
            List<DiscountDTO> discounts= new ArrayList<>();
            try {
                preparedStatement = connection.prepareStatement("SELECT d.code, d.discount_percent, p.name as product_name FROM discounts d JOIN products p ON d.product_code = p.code");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int code = resultSet.getInt("code");
                    String productName = resultSet.getString("product_name");
                    int discountPercent = resultSet.getInt("discount_percent");
                    discounts.add(new DiscountDTO(code, productName, discountPercent));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return discounts;
        }

        public DiscountDTO getDiscountByCode(int CODE) {

            try {
                preparedStatement = connection.prepareStatement("Select * FROM discounts Where code = ?");
                preparedStatement.setInt(1, CODE);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                  int code = resultSet.getInt("code");
                  int productCode = resultSet.getInt("product_code");
                  int percentDiscount = resultSet.getInt("discount_percent");
                    // Create and return a Customer object
                    return new DiscountDTO(code,productCode,percentDiscount);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        public void addDiscount(DiscountDTO discount) {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO discounts (product_code, discount_percent) VALUES (?, ?)");
                preparedStatement.setInt(1, discount.getProductCode());
                preparedStatement.setInt(2, discount.getDiscountPercent());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }





    }

