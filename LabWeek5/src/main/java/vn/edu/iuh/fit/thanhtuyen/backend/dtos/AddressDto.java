package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.Address}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto implements Serializable {
    Long id;
    String street;
    String city;
    CountryCode country;
    String number;
    String zipcode;
}