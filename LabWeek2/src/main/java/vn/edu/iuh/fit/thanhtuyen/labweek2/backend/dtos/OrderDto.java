package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDto implements Serializable {
    Long id;
    LocalDateTime orderDate;
    CustomerDto customer;
    EmployeeDto employee;
    List<OrderDetailDto> orderDetails;
}