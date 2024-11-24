package vn.edu.iuh.fit.thanhtuyen.backend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "company")
@PrimaryKeyJoinColumn(name = "com_id")
public class Company extends User{
    @Column(name = "about", length = 2000)
    private String about;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "comp_name", nullable = false)
    private String compName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "web_url")
    private String webUrl;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Job> jobs;
}