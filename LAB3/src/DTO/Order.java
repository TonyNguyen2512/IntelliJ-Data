package DTO;

public class Order {
    String productId;
    String name;
    Integer quantity;

    public Order(String name, String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}