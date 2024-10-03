package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.converters.ProductStatusConverter;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.enums.ProductStatus;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Product.findByManufacturer",
                query = "SELECT p FROM Product p WHERE p.manufacturer = :manufacturer"),
        @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status")
})
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "manufacturer_name", length = 100)
    private String manufacturer;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus status;

    @Column(name = "unit", length = 25)
    private String unit;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductPrice> productPrices;
}
