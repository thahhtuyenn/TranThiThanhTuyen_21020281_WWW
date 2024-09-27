package vn.edu.iuh.fit.thanhtuyen.labweek2.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPrice}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductPriceDto implements Serializable {
    private Long productId;
    private LocalDateTime priceDateTime;
    private String note;
    private Double price;
}