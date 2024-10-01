package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Customer;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CustomerDto implements Serializable {
    Long id;
    String address;
    String name;
    String email;
    String phone;
}