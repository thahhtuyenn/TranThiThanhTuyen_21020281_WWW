package vn.edu.iuh.fit.thanhtuyen.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidate_skill")
@Builder
public class CandidateSkill {
    @EmbeddedId
    private CandidateSkillId id;

    @MapsId("canId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate candidate;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @Column(name = "skill_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;


}