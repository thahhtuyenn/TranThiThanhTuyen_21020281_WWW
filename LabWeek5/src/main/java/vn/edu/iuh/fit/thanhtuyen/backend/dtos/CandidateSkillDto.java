package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.CandidateSkill}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateSkillDto implements Serializable {
    Long skillId;
    Long canId;
    String moreInfos;
    SkillLevel skillLevel;
}