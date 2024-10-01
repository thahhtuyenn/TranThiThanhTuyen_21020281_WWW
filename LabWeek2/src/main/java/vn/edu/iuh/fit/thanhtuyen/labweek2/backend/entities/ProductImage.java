package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ProductImage.findByProductId",
                query = "SELECT pi FROM ProductImage pi WHERE pi.product.id = :productId")
})
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "alternative", length = 250)
    private String alternative;

    @Column(name = "path", length = 250)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    // Getters và Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}