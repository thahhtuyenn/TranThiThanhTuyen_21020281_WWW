package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    List<RoleDto> roles;
}