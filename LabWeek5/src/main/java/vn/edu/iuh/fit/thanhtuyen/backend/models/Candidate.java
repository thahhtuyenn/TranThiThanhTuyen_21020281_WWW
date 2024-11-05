package vn.edu.iuh.fit.thanhtuyen.backend.models;

import jakarta.persistence.*;
import lombok.*;

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
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    private List<CandidateSkill> candidateSkills;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    private List<Experience> experiences;

    public Candidate(LocalDate dob, String email, String fullName, String phone, Address address) {
        this.dob = dob;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }
}