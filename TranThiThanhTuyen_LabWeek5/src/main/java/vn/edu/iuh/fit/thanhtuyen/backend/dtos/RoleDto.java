package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.Role}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoleDto implements Serializable {
    Long id;
    String name;
    String code;
}