package dto;

import java.util.Date;

public class TransactionDTO {

    int transactionID;
    private  String salesrepName;


    private int salesrepID;
    private String customerName;

    private int customerID;
    private String productName;

    private int productCode;

    private  int discountCode;
    private  String date;
    private  int quantity;


    private  int totalAmount;




    public TransactionDTO(int transactionID, String salesrepName, String customerName, String productName ,int discountCode, String date, int quantity,  int totalAmount){
        this.transactionID = transactionID;
        this.salesrepName = salesrepName;
        this.customerName = customerName;
        this.productName = productName;
        this.discountCode = discountCode;
        this.date = date;
        this.quantity = quantity;
        this.totalAmount = totalAmount;

    }

    public TransactionDTO(int salesrepID, int customerID, int productCode, String date, int quantity, int discountCode, int totalAmount){
        this.salesrepID = salesrepID;
        this.customerID = customerID;
        this.productCode = productCode;

        this.date = date;
        this.quantity = quantity;
        this.discountCode = discountCode;
        this.totalAmount = totalAmount;

    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getSalesrepName() {
        return salesrepName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getSalesrepID() {
        return salesrepID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getProductCode() {
        return productCode;
    }


    public String getProductName() {
        return productName;
    }

    public int getDiscountCode() {
        return discountCode;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }



    public int getTotalAmount() {
        return totalAmount;
    }







}
