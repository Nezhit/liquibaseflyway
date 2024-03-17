package cosmonaut.entity;

import cosmonaut.entity.enums.ProductType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "arrival_time")
    private LocalDate arrivalTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    public Product() {}

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public ProductType getProductType() {
        return productType;
    }

    @Override
    public String toString() {
        return "Product [" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ']';
    }
}
