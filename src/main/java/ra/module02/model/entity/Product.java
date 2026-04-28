package ra.module02.model.entity;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private Integer stock;

    public Product() {
    }

    public Product(Long id, String name, String brand, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void showProduct() {
        System.out.printf("|%-4d|%-40s|%-30s|%12s|%8d|\n",
                            id, name, brand, price, stock);
    }
}
