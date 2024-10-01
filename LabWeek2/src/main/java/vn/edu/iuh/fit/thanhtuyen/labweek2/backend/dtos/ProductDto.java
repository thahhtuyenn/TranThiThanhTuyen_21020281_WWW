package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Product;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.ProductStatus;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Product}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductDto implements Serializable {
    private Long id;
    private String description;
    private String manufacturer;
    private String name;
    private ProductStatus status;
    private String unit;
    private List<ProductImageDto> productImages;
    private List<ProductPriceDto> productPrices;
}