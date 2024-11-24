package vn.edu.iuh.fit.thanhtuyen;

import com.neovisionaries.i18n.CountryCode;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillType;
import vn.edu.iuh.fit.thanhtuyen.backend.models.*;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.*;

import java.time.LocalDate;
import java.util.*;


@SpringBootTest
class TranThiThanhTuyenLabWeek5ApplicationTests {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private JobRepository jobRepository;

    @Test
    void insertDataAddress() {
        Faker faker = new Faker();
        for (int i = 1; i <= 100; i++) {
            String street = faker.address().streetName();
            String city = faker.address().city();
            String zip = faker.address().zipCode();
            String number = faker.address().buildingNumber();
            CountryCode cc = CountryCode.valueOf(faker.address().countryCode());
            Address add = Address.builder()
                    .street(street)
                    .zipcode(zip)
                    .city(city)
                    .country(cc)
                    .number(number)
                    .id((long) i)
                    .build();
            addressRepository.save(add);
        }
    }

    @Test
    void insertDataCompany() {

    }

    @Test
    void insertDataCandidate() {
    }

    @Test
    void insertDataExperience() {
        Faker faker = new Faker(new Locale("en"));
        for (int i = 1; i < 150; i++) {
            String companyName = faker.company().name();
            LocalDate fromDate = faker.date().birthdayLocalDate(18, 60);
            LocalDate toDate = faker.date().birthdayLocalDate(18, 60);
            if (fromDate.isAfter(toDate)) {
                LocalDate temp = fromDate;
                fromDate = toDate;
                toDate = temp;
            }
            String role = faker.company().profession();
            String description = faker.lorem().sentence();
            Optional<Candidate> can = candidateRepository.findById((long) faker.random().nextInt(1, 100));
            Candidate candidate = can.orElse(null);
            if (candidate != null) {
                Experience exp = Experience.builder()
                        .companyName(companyName)
                        .fromDate(fromDate)
                        .toDate(toDate)
                        .role(role)
                        .workDescription(description)
                        .candidate(candidate)
                        .id((long) i)
                        .build();
                experienceRepository.save(exp);
            }
        }
    }

    @Test
    void insertDataSkill() {
        List<String> skills = List.of(
                "Java",
                "Python",
                "SQL",
                "JavaScript",
                "HTML/CSS",
                "React",
                "Angular",
                "Spring Boot",
                "Node.js",
                "C#",
                "Ruby on Rails",
                "Git",
                "Docker",
                "Kubernetes",
                "AWS (Amazon Web Services)",
                "Azure",
                "Machine Learning",
                "Data Analysis",
                "Cloud Computing",
                "Cybersecurity",
                "RESTful API",
                "Agile Methodology",
                "DevOps",
                "Jenkins",
                "TypeScript",
                "Swift",
                "Kotlin",
                "R (Programming Language)",
                "MATLAB",
                "Sass/Less",
                "GraphQL",
                "TensorFlow",
                "Flask",
                "Django",
                "Vue.js",
                "MongoDB",
                "PostgreSQL",
                "MySQL",
                "Apache Kafka",
                "Hadoop",
                "Spark",
                "Figma",
                "UI/UX Design",
                "Bootstrap",
                "Tailwind CSS",
                "Salesforce",
                "Power BI",
                "Tableau",
                "Business Analysis",
                "Quality Assurance",
                "Unit Testing"
        );

        Faker faker = new Faker(new Locale("en"));
        SkillType[] skillTypes = SkillType.values();
        for (int i = 0; i < skills.size(); i++) {
            SkillType skillType = skillTypes[faker.random().nextInt(skillTypes.length)];
            String skillName = skills.get(i);
            String description = faker.lorem().sentence();
            skillRepository.save(Skill.builder()
                    .skillName(skillName)
                    .skillDescription(description)
                    .type(skillType)
                    .id((long) i + 1)
                    .build());
        }
    }

    @Test
    void insertDataJob() {
        Faker faker = new Faker(new Locale("en"));
        for (int i = 1; i <= 150; i++) {
            String jobName = faker.job().position();
            String jobDesc = faker.lorem().sentence();
            Optional<Company> comp = companyRepository.findById((long) faker.random().nextInt(1, 100));
            Company company = comp.orElse(null);
            if (company != null) {
                Job job = Job.builder()
                        .jobName(jobName)
                        .jobDesc(jobDesc)
                        .company(company)
                        .id((long) i)
                        .build();
                jobRepository.save(job);
            }
        }

    }

    @Test
    void insertDataJobSkill() {
        Faker faker = new Faker();
        SkillLevel[] skillLevels = SkillLevel.values();

        for (int i = 1; i <= 150; i++) {
            int sizeSkill = faker.random().nextInt(3, 10);
            for (int j = 0; j < sizeSkill; j++) {
                String moreInfos = faker.lorem().sentence();
                int skillLevel = skillLevels[faker.random().nextInt(skillLevels.length)].getValue();
                int skillId = faker.number().numberBetween(1, 50);
                String insertStatement = String.format(
                        "INSERT INTO job_skill (more_infos, skill_level, skill_id, job_id) VALUES ('%s', '%s', %d, %d);",
                        moreInfos, skillLevel, skillId, i
                );
                System.out.println(insertStatement);
            }
        }

    }

    @Test
    void insertDataCandidateSkill() {
        Faker faker = new Faker();
        SkillLevel[] skillLevels = SkillLevel.values();

        for (int i = 1; i <= 100; i++) {
            List<CandidateSkillId> candidateSkillIds = new ArrayList<>();
            int sizeSkill = faker.random().nextInt(3, 10);
            int j = 0;
            while (j < sizeSkill) {
                String moreInfos = faker.lorem().sentence();
                int skillLevel = skillLevels[faker.random().nextInt(skillLevels.length)].getValue();
                int skillId = faker.number().numberBetween(1, 50);
                CandidateSkillId candidateSkillId = new CandidateSkillId((long) i, (long) skillId);
                if(!candidateSkillIds.contains(candidateSkillId)) {
                    candidateSkillIds.add(candidateSkillId);
                    String insertStatement = String.format(
                            "INSERT INTO candidate_skill (more_infos, skill_level, skill_id, can_id) VALUES ('%s', '%s', %d, %d);",
                            moreInfos, skillLevel, skillId, i
                    );
                    System.out.println(insertStatement);
                    j++;
                }
            }
        }

    }
}
