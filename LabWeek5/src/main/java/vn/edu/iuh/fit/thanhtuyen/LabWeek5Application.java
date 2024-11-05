package vn.edu.iuh.fit.thanhtuyen;

import com.neovisionaries.i18n.CountryCode;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Address;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CompanyRepository;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@SpringBootApplication
public class LabWeek5Application {

    public static void main(String[] args) {
        SpringApplication.run(LabWeek5Application.class, args);
    }


//    @Bean
//    CommandLineRunner initData(){
//        return args -> {
//
//
//            }
//        };
//    }
}
