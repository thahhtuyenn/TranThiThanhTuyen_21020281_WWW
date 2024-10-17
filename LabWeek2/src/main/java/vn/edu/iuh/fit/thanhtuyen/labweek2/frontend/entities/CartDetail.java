package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartDetail {
    Long cartId;
    ProductDto product;
    String note;
    Double price;
    Double quantity;

    public double getMoney(){
        return quantity * price;
    }
}
