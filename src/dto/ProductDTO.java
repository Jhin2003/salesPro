package dto;

public class ProductDTO {
    private int code;
    private String categoryName;

    private int categoryID;

    private String name;
    private String description;

    private int price;

    private int stockQuantity;
    private int soldQuantity;
    private int reorderPoint;

    public ProductDTO(int code, String categoryName, String name, String description, int price, int stockQuantity, int soldQuantity, int reorderPoint){
        this.code = code;
        this.categoryName = categoryName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.soldQuantity = soldQuantity;
        this.reorderPoint = reorderPoint;
    }

    public ProductDTO( int categoryID, String name, String description, int price, int stockQuantity, int soldQuantity, int reorderPoint){

        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.soldQuantity = soldQuantity;
        this.reorderPoint = reorderPoint;
    }

    public ProductDTO(int code, int categoryID, String name, String description, int price, int stockQuantity, int soldQuantity, int reorderPoint){

        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.soldQuantity = soldQuantity;
        this.reorderPoint = reorderPoint;
    }


    public int getCode(){
        return code;
    }
    public String getCategoryName(){
        return categoryName;
    }
    public int getCategoryID(){return categoryID;}
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public int getPrice(){
        return price;
    }
    public int getStockQuantity(){
        return stockQuantity;
    }
    public int getSoldQuantity(){
        return soldQuantity;
    }
    public int getReorderPoint(){
        return reorderPoint;
    }

}
