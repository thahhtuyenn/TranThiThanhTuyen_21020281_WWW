package vn.edu.iuh.fit.thanhtuyen.backend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidate")
@SuperBuilder
@PrimaryKeyJoinColumn(name = "can_id")
public class Candidate extends User{
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CandidateSkill> candidateSkills;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Experience> experiences;
}