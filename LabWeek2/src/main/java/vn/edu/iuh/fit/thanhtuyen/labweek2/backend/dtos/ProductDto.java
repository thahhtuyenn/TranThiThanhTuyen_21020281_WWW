package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Product;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.ProductStatus;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getImage() {
        if (productImages != null) {
            if (!productImages.isEmpty()) {
                return productImages.get(0).getPath();
            }
        }
        return "";
    }

    public double getPrice() {
        double price = 0d;
        if (productPrices != null) {
            if (!productPrices.isEmpty()) {
                price = productPrices.stream()
                        .sorted(
                                Comparator.comparing(ProductPriceDto::getPriceDateTime).reversed()
                        ).collect(Collectors.toList()).get(0).getPrice();
            }
        }
        return price;
    }
}