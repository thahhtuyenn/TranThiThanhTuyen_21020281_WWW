package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillType;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.thanhtuyen.backend.models.Skill}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SkillDto implements Serializable {
    Long id;
    String skillDescription;
    String skillName;
    SkillType type;
}