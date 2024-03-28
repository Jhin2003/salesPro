package dto;

public class SalesrepDTO {
    private int id;
    private String name;

    private String contact;
    private String address;

    public SalesrepDTO(int id, String name, String contact, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public SalesrepDTO(String name, String contact, String address) {
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

