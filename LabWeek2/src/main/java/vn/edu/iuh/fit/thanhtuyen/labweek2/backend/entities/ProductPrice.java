package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_prices")

@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ProductPrice.findByProductId", query = "SELECT p FROM ProductPrice p WHERE p.product.id = :productId"),
        @NamedQuery(name = "ProductPrice.findLastPriceByProductId", query = "SELECT p FROM ProductPrice p WHERE p.product.id = :productId ORDER BY p.id.priceDateTime DESC")
})
public class ProductPrice {
    @EmbeddedId
    private ProductPriceId id;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    // Getters và Setters

    public ProductPriceId getId() {
        return id;
    }

    public void setId(ProductPriceId id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}