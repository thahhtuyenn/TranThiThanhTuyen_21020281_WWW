package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.EmployeeStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Employee}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeDto implements Serializable {
    Long id;
    String address;
    LocalDateTime dob;
    String email;
    String fullName;
    String phone;
    EmployeeStatus status;
}