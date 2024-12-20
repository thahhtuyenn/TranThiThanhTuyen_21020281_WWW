package vn.edu.iuh.fit.thanhtuyen.backend.models;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "address")
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street", length = 150)
    private String street;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country")
    private CountryCode country;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "zipcode", length = 15)
    private String zipcode;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Candidate candidate;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Company company;

    public Address(String street, String city, CountryCode country, String number, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.zipcode = zipcode;
    }
}