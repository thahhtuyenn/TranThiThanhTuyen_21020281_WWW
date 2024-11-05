package vn.edu.iuh.fit.thanhtuyen.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillType;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill")
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SkillType type;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<JobSkill> jobSkills;

    public Skill(String skillDescription, String skillName, SkillType type) {
        this.skillDescription = skillDescription;
        this.skillName = skillName;
        this.type = type;
    }
}