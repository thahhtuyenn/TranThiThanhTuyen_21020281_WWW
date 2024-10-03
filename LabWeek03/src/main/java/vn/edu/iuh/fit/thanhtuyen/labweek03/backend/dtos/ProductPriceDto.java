package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
