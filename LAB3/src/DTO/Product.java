package DTO;

public class Product {
    private String categoryID;
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String categoryID, String id, String name, double price, int quantity) {
        this.categoryID = categoryID;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return String.format("%s;%s;%s;%s;%s", categoryID, id, name, price, quantity);
    }

    public boolean equals(Object product) {
        return this.getId().equals(((Product) product).getId());
    }


}
