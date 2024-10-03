package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.enums.ProductStatus;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<ProductPriceDto> productPrices;

    public double getPrice() {
        double price = 0d;
        if (productPrices != null) {
            if (!productPrices.isEmpty()) {
                price = productPrices.get(0).getPrice();
            }
        }

        return price;
    }

    /**
     * Lấy giá mới nhất của sản phẩm
     *
     * @return giá mới nhất
     */
    public double getPriceLatest() {

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
