package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cart {
    Long id;
    LocalDateTime cartDate;
    CustomerDto customer;
    EmployeeDto employee;
    List<CartDetail> cartDetails;

    public double getTotal(){
        double total = 0;
        if (cartDetails != null && !cartDetails.isEmpty()){
            for (CartDetail cartDetail : cartDetails){
                total += cartDetail.getMoney();
            }
        }
        return total;
    }

}
