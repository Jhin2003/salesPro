package dto;

public class CustomerDTO {
    private int id;
    private String name;

    private String contact;
    private String address;

    public CustomerDTO(int id, String name, String contact, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public CustomerDTO( String name, String contact, String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}

