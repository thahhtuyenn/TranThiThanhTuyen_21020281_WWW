package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.Experience}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExperienceDto implements Serializable {
    Long id;
    LocalDate toDate;
    LocalDate fromDate;
    String companyName;
    String role;
    String workDescription;
}