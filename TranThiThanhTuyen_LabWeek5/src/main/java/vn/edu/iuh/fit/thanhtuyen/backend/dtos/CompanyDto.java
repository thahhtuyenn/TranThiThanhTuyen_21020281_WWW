package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.Company}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class CompanyDto implements Serializable {
    Long id;
    String about;
    String email;
    String compName;
    String phone;
    String webUrl;
    AddressDto address;

}