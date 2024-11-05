package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.JobSkill}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobSkillDto implements Serializable {
    Long jobId;
    Long skillId;
    SkillDto skill;
    String moreInfos;
    SkillLevel skillLevel;
}