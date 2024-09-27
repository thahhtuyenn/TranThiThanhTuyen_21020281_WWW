package vn.edu.iuh.fit.thanhtuyen.labweek2.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.OrderDetail;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductImage;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPrice;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.ProductStatus;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Product}
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