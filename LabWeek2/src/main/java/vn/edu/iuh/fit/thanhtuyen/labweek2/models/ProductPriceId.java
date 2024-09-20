package vn.edu.iuh.fit.thanhtuyen.labweek2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class ProductPriceId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "price_date_time")
    private LocalDateTime priceDateTime;


    public ProductPriceId() {
    }

    public ProductPriceId(Long productId, LocalDateTime priceDateTime) {
        this.productId = productId;
        this.priceDateTime = priceDateTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getPriceDateTime() {
        return priceDateTime;
    }

    public void setPriceDateTime(LocalDateTime priceDateTime) {
        this.priceDateTime = priceDateTime;
    }
}
