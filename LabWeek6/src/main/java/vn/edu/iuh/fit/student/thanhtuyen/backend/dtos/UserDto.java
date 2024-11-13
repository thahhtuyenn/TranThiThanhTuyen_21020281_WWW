package vn.edu.iuh.fit.student.thanhtuyen.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link vn.edu.iuh.fit.student.thanhtuyen.backend.entities.User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto implements Serializable {
    Long id;
    String firstName;
    String middleName;
    String lastName;
    String mobile;
    String email;
    String passwordHash;
    Instant registeredAt;
    Instant lastLogin;
    String intro;
    String profile;
}