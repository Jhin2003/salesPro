package dto;

public class DiscountDTO {
    int code;
    String productName;

    int productCode;

    int discountPercent;

    public DiscountDTO(int code, String productName, int discountPercent){
           this.code = code;
           this.productName = productName;
           this.discountPercent = discountPercent;
    }

    public DiscountDTO( int code, int productCode, int discountPercent){
        this.code = code;
        this.productCode = productCode;
        this.discountPercent = discountPercent;
    }


    public DiscountDTO( int productCode, int discountPercent){

        this.productCode = productCode;
        this.discountPercent = discountPercent;
    }


    public int getCode() {
        return code;
    }

    public int getProductCode(){
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}
