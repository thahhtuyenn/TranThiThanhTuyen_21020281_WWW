package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cart {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;

    public double getMoney(){
        return quantity * price;
    }
}
