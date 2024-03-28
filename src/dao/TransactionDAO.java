package dao;

import db.ConnectionFactory;
import dto.TransactionDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TransactionDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public TransactionDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public List<TransactionDTO> getCustomerPurchaseTransactionsByID(int ID){
        List<TransactionDTO> customerPurchaseTransactions = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  t.id, t.date, t.quantity, t.total_amount, p.name as product_name , c.name as customer_name, s.name as salesrep_name, d.code as discount_code\n" +
                    "from transactions t JOIN customers c ON t.customer_id = c.id JOIN products p ON t.product_code = p.code JOIN salesreps s on t.salesrep_id = s.id  JOIN discounts d on t.discount_code = d.code WHERE t.customer_id = ?");
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int transactionID = resultSet.getInt("id");
                String date = resultSet.getString("date");
                int quantity = resultSet.getInt("quantity");
                int discountCode = resultSet.getInt("discount_code");
                int total_amount = resultSet.getInt("total_amount");
                String salesrepName = resultSet.getString("salesrep_name");
                String customerName = resultSet.getString("customer_name");
                String productName = resultSet.getString("product_name");

                customerPurchaseTransactions.add(new TransactionDTO(transactionID, salesrepName, customerName, productName, discountCode, date, quantity,  total_amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerPurchaseTransactions;
    }

    public List<TransactionDTO> getSalesrepSalesTransactionsByID(int ID){
        List<TransactionDTO> salesrepSalesTransactions = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  t.id, t.date, t.quantity, t.total_amount, p.name as product_name , c.name as customer_name, s.name as salesrep_name, d.code as discount_code\n" +
                    "from transactions t JOIN customers c ON t.customer_id = c.id JOIN products p ON t.product_code = p.code JOIN salesreps s on t.salesrep_id = s.id  JOIN discounts d on t.discount_code = d.code WHERE t.salesrep_id = ?");
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int transactionID = resultSet.getInt("id");
                String date = resultSet.getString("date");
                int quantity = resultSet.getInt("quantity");
                int discountCode = resultSet.getInt("discount_code");
                int total_amount = resultSet.getInt("total_amount");
                String salesrepName = resultSet.getString("salesrep_name");
                String customerName = resultSet.getString("customer_name");
                String productName = resultSet.getString("product_name");

                salesrepSalesTransactions.add(new TransactionDTO(transactionID, salesrepName, customerName, productName, discountCode, date, quantity, total_amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesrepSalesTransactions;
    }

    public List<TransactionDTO> getProductSellTransactionsByCode(int CODE){
        List<TransactionDTO> productSellTransactions = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  t.id, t.date, t.quantity, t.total_amount, p.name as product_name , c.name as customer_name, s.name as salesrep_name, d.code as discount_code\n" +
                    "from transactions t JOIN customers c ON t.customer_id = c.id JOIN products p ON t.product_code = p.code JOIN salesreps s on t.salesrep_id = s.id  JOIN discounts d on t.discount_code = d.code WHERE t.product_code = ?");
            preparedStatement.setInt(1, CODE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int transactionID = resultSet.getInt("id");
                String date = resultSet.getString("date");
                int quantity = resultSet.getInt("quantity");
                int discountCode = resultSet.getInt("discount_code");
                int total_amount = resultSet.getInt("total_amount");
                String salesrepName = resultSet.getString("salesrep_name");
                String customerName = resultSet.getString("customer_name");
                String productName = resultSet.getString("product_name");

                productSellTransactions.add(new TransactionDTO(transactionID, salesrepName, customerName, productName, discountCode, date, quantity,  total_amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productSellTransactions;
    }

    public void addCustomerPurchaseTransaction(TransactionDTO transactionDTO) {

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO transactions (salesrep_id, customer_id, product_code,  discount_code, date, quantity, total_amount) VALUES (?, ?, ?, ?,?,?,?)");
            preparedStatement.setInt(1, transactionDTO.getSalesrepID() );
            preparedStatement.setInt(2, transactionDTO.getCustomerID());
            preparedStatement.setInt(3, transactionDTO.getProductCode());
            preparedStatement.setInt(4, transactionDTO.getDiscountCode());
            preparedStatement.setString(5, transactionDTO.getDate());
            preparedStatement.setInt(6, transactionDTO.getQuantity());
            preparedStatement.setInt(7, transactionDTO.getTotalAmount());
            preparedStatement.executeUpdate();

            // Decrease stock of the product
            decreaseProductStock(transactionDTO.getProductCode(), transactionDTO.getQuantity());
            increaseSoldQuantity(transactionDTO.getProductCode(), transactionDTO.getQuantity());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void decreaseProductStock(int productCode, int quantity) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET stock_quantity = stock_quantity - ? WHERE code = ?");
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void increaseSoldQuantity(int productCode, int quantity) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET sold_quantity = sold_quantity + ? WHERE code = ?");
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

