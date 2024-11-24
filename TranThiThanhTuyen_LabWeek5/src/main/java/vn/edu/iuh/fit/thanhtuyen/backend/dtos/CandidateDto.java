package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class CandidateDto implements Serializable{
    Long id;
    LocalDate dob;
    String email;
    String fullName;
    String phone;
    AddressDto address;
    List<CandidateSkillDto> candidateSkills;
    List<ExperienceDto> experiences;
}