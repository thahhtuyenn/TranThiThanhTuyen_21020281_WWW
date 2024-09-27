package vn.edu.iuh.fit.thanhtuyen.labweek2.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.EmployeeStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Employee}
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