package vn.edu.iuh.fit.thanhtuyen;

import com.neovisionaries.i18n.CountryCode;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Test
    void insertDataRole() {
        Role role1 = Role.builder().name("admin").code("ADMIN").build();
        Role role2 = Role.builder().name("company").code("COMPANY").build();
        Role role3 = Role.builder().name("candidate").code("CANDIDATE").build();
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }

    @Test
    void insertDataCompany() {
        Optional<Role> role = roleRepository.findByCode("COMPANY");
        if (role.isEmpty()) {
            return;
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role.get());
        Faker faker = new Faker(new Locale("vi"));
        Faker faker2 = new Faker(new Locale("en"));

        for (int i = 1; i <= 50; i++) {
            Company c = Company.builder()
                    .roles(roles)
                    .about(faker.lorem().sentence())
                    .phone(faker.phoneNumber().phoneNumber())
                    .email(faker.siliconValley().email())
                    .address(Address.builder()
                            .city(faker.address().city())
                            .country(CountryCode.valueOf(faker.address().countryCode()))
                            .zipcode(faker.address().zipCode())
                            .street(faker.address().streetAddress())
                            .number(faker.address().streetAddressNumber())
                            .build())
                    .webUrl(faker.internet().url())
                    .compName(faker.company().name())
                    .username(faker2.internet().username())
                    .password(passwordEncoder.encode("123456"))
                    .build();
            companyRepository.saveAndFlush(c);
        }
    }

    @Test
    void insertDataCandidate() {
        Optional<Role> role = roleRepository.findByCode("CANDIDATE");
        if (role.isEmpty()) {
            return;
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role.get());
        Faker faker = new Faker(new Locale("vi"));
        Faker faker2 = new Faker(new Locale("en"));
        for (int i = 1; i <= 50; i++) {
            Candidate c = Candidate.builder()
                    .roles(roles)
                    .dob(faker.date().birthdayLocalDate(18, 60))
                    .fullName(faker.name().fullName())
                    .phone(faker.phoneNumber().phoneNumber())
                    .email(faker.siliconValley().email())
                    .address(Address.builder()
                            .city(faker.address().city())
                            .country(CountryCode.valueOf(faker.address().countryCode()))
                            .zipcode(faker.address().zipCode())
                            .street(faker.address().streetAddress())
                            .number(faker.address().streetAddressNumber())
                            .build())
                    .username(faker2.internet().username())
                    .password(passwordEncoder.encode("123456"))
                    .build();
            candidateRepository.saveAndFlush(c);
        }
    }

    @Test
    void insertDataExperience() {
        Faker faker = new Faker(new Locale("vi"));
        Random random = new Random();
        for (int i = 51; i <= 100; i++) {
            Optional<Candidate> can = candidateRepository.findById((long) i);
            int sizeExp = random.nextInt(3, 6);
            for (int j = 0; j < sizeExp; j++) {
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
    }

    @Test
    void insertDataSkill() {
        List<String> skills = List.of(
                "Java", "Python", "JS", "TS", "C++", "C#", "PHP", "Ruby", "Go", "Swift", "Kotlin",
                "Rust", "HTML", "CSS", "React", "Vue", "Angular", "Next", "Node", "Express", "Spring",
                "Django", "Flask", "Flutter", "MySQL", "Postgres", "Mongo", "Redis", "Docker", "K8s", "Git", "CI/CD",
                "AWS", "Azure", "GCP", "Jenkins", "Terraform", "Ansible", "PowerBI", "Tableau", "BigData", "ML",
                "DL", "NLP", "TF", "PyTorch", "Agile", "Scrum", "Figma", "Xamarin", "GraphQL");

        Faker faker = new Faker(new Locale("vi"));
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
        Random random = new Random();
        List<Map<String, String>> jobTitles = List.of(
                Map.of("title", "Software Engineer", "description", "Thiết kế, phát triển và duy trì các ứng dụng phần mềm."),
                Map.of("title", "Frontend Developer", "description", "Xây dựng giao diện người dùng và chức năng phía client của các trang web và ứng dụng."),
                Map.of("title", "Backend Developer", "description", "Tập trung vào logic phía server, cơ sở dữ liệu và API cho các ứng dụng web."),
                Map.of("title", "Fullstack Developer", "description", "Làm việc cả về phát triển frontend và backend của các ứng dụng web."),
                Map.of("title", "Mobile App Developer", "description", "Chuyên phát triển ứng dụng di động cho các nền tảng iOS và Android."),
                Map.of("title", "Game Developer", "description", "Thiết kế và phát triển các trò chơi điện tử cho các nền tảng khác nhau."),
                Map.of("title", "Data Scientist", "description", "Phân tích dữ liệu phức tạp để rút ra các thông tin và hỗ trợ ra quyết định."),
                Map.of("title", "Machine Learning Engineer", "description", "Phát triển và triển khai các mô hình và thuật toán học máy."),
                Map.of("title", "AI Engineer", "description", "Xây dựng và triển khai các hệ thống và ứng dụng trí tuệ nhân tạo."),
                Map.of("title", "DevOps Engineer", "description", "Tự động hóa và tối ưu hóa các quy trình phát triển, kiểm thử và triển khai."),
                Map.of("title", "Cloud Engineer", "description", "Quản lý và triển khai các cơ sở hạ tầng và dịch vụ dựa trên đám mây."),
                Map.of("title", "Cybersecurity Analyst", "description", "Bảo vệ hệ thống và mạng khỏi các mối đe dọa và tấn công mạng."),
                Map.of("title", "IT Support Specialist", "description", "Cung cấp hỗ trợ kỹ thuật và giải quyết các vấn đề IT cho người dùng."),
                Map.of("title", "System Administrator", "description", "Duy trì và quản lý các hệ thống và máy chủ IT."),
                Map.of("title", "Database Administrator", "description", "Đảm bảo cơ sở dữ liệu an toàn, tối ưu và được bảo trì đúng cách."),
                Map.of("title", "Network Engineer", "description", "Thiết kế và quản lý các mạng máy tính để đảm bảo kết nối và hiệu suất."),
                Map.of("title", "UX/UI Designer", "description", "Tạo giao diện người dùng thân thiện và hấp dẫn cho các ứng dụng và trang web."),
                Map.of("title", "Product Manager", "description", "Giám sát quá trình phát triển sản phẩm và đảm bảo tính đồng nhất với các mục tiêu kinh doanh."),
                Map.of("title", "IT Project Manager", "description", "Quản lý các dự án IT, đảm bảo hoàn thành đúng hạn và trong ngân sách."),
                Map.of("title", "Technical Writer", "description", "Viết và duy trì tài liệu kỹ thuật cho các sản phẩm và hệ thống."),
                Map.of("title", "Quality Assurance (QA) Tester", "description", "Kiểm thử phần mềm để phát hiện lỗi và đảm bảo chất lượng."),
                Map.of("title", "Automation Tester", "description", "Phát triển các kịch bản tự động để kiểm thử các ứng dụng phần mềm."),
                Map.of("title", "IT Consultant", "description", "Cung cấp tư vấn chuyên môn về các chiến lược và giải pháp IT cho doanh nghiệp."),
                Map.of("title", "Business Analyst", "description", "Kết nối giữa các nhu cầu kinh doanh và giải pháp kỹ thuật."),
                Map.of("title", "Big Data Engineer", "description", "Thiết kế và xây dựng các hệ thống để xử lý và phân tích các bộ dữ liệu lớn."),
                Map.of("title", "Blockchain Developer", "description", "Phát triển các ứng dụng và hệ thống phi tập trung sử dụng công nghệ blockchain."),
                Map.of("title", "AR/VR Developer", "description", "Tạo ứng dụng thực tế ảo và thực tế tăng cường cho các mục đích sử dụng khác nhau."),
                Map.of("title", "Embedded Systems Engineer", "description", "Phát triển phần mềm và phần cứng cho các hệ thống nhúng như các thiết bị IoT."),
                Map.of("title", "IoT Developer", "description", "Thiết kế và xây dựng các giải pháp cho hệ sinh thái Internet of Things (IoT)."),
                Map.of("title", "IT Operations Manager", "description", "Giám sát các hoạt động IT, cơ sở hạ tầng và đội ngũ hỗ trợ để đảm bảo quy trình làm việc suôn sẻ.")
        );
        for (int i = 1; i <= 50; i++) {
            Optional<Company> comp = companyRepository.findById((long) i);
            int sizeJobs = faker.random().nextInt(3, 10);
            int j = 0;
            List<String> jobs = new ArrayList<>();
            while (j < sizeJobs) {
                Map<String, String> jobNew = jobTitles.get(faker.random().nextInt(jobTitles.size()));
                if (!jobs.contains(jobNew.get("title"))) {
                    jobs.add(jobNew.get("title"));
                    Company company = comp.orElse(null);
                    if (company != null) {
                        Job job = Job.builder()
                                .jobName(jobNew.get("title"))
                                .jobDesc("<p>" + jobNew.get("description") + "</p>")
                                .company(company)
                                .id(0L)
                                .build();
                        jobRepository.save(job);
                    }
                    j++;
                }
            }
        }

    }

    @Test
    void insertDataJobSkill() {
        Faker faker = new Faker();
        SkillLevel[] skillLevels = SkillLevel.values();
        List<Map<String, Object>> skillAndInfos = List.of(
                Map.of("skillId", 1L, "info", "Lập trình phần mềm, ứng dụng web và hệ thống với Java."),
                Map.of("skillId", 2L, "info", "Phân tích dữ liệu và phát triển các ứng dụng khoa học dữ liệu với Python."),
                Map.of("skillId", 3L, "info", "Phát triển giao diện người dùng và ứng dụng web với JavaScript."),
                Map.of("skillId", 4L, "info", "Phát triển ứng dụng web với TypeScript, cải thiện chất lượng mã nguồn."),
                Map.of("skillId", 5L, "info", "Lập trình ứng dụng với C++, tối ưu hóa hiệu suất và hệ thống."),
                Map.of("skillId", 6L, "info", "Phát triển ứng dụng và phần mềm với C#, xây dựng hệ thống đa nền tảng."),
                Map.of("skillId", 7L, "info", "Phát triển web với PHP, tích hợp cơ sở dữ liệu và hệ thống."),
                Map.of("skillId", 8L, "info", "Xây dựng ứng dụng web và phần mềm với Ruby."),
                Map.of("skillId", 9L, "info", "Lập trình với Go, xây dựng các ứng dụng có hiệu suất cao."),
                Map.of("skillId", 10L, "info", "Phát triển ứng dụng di động và phần mềm với Swift."),
                Map.of("skillId", 11L, "info", "Xây dựng ứng dụng di động với Kotlin cho Android."),
                Map.of("skillId", 12L, "info", "Phát triển các ứng dụng hiệu suất cao và các công cụ hệ thống với Rust."),
                Map.of("skillId", 13L, "info", "Xây dựng giao diện web tĩnh và động với HTML."),
                Map.of("skillId", 14L, "info", "Thiết kế và bố trí giao diện web với CSS."),
                Map.of("skillId", 15L, "info", "Phát triển giao diện người dùng với React, tạo ứng dụng web động."),
                Map.of("skillId", 16L, "info", "Phát triển ứng dụng web với Vue, xây dựng giao diện người dùng tương tác."),
                Map.of("skillId", 17L, "info", "Xây dựng các ứng dụng đơn trang (SPA) với Angular."),
                Map.of("skillId", 18L, "info", "Phát triển ứng dụng web với Next.js, tối ưu hóa hiệu suất."),
                Map.of("skillId", 19L, "info", "Lập trình và phát triển ứng dụng backend với Node.js."),
                Map.of("skillId", 20L, "info", "Xây dựng ứng dụng web nhanh chóng với Express.js."),
                Map.of("skillId", 21L, "info", "Phát triển các ứng dụng web Java với Spring Framework."),
                Map.of("skillId", 22L, "info", "Xây dựng ứng dụng web mạnh mẽ với Django."),
                Map.of("skillId", 23L, "info", "Phát triển ứng dụng web với Flask, một framework Python nhẹ."),
                Map.of("skillId", 24L, "info", "Phát triển ứng dụng di động với Flutter."),
                Map.of("skillId", 25L, "info", "Quản lý cơ sở dữ liệu quan hệ với MySQL."),
                Map.of("skillId", 26L, "info", "Quản lý và phát triển cơ sở dữ liệu với PostgreSQL."),
                Map.of("skillId", 27L, "info", "Lưu trữ và truy vấn dữ liệu không cấu trúc với MongoDB."),
                Map.of("skillId", 28L, "info", "Lưu trữ và truy xuất dữ liệu nhanh chóng với Redis."),
                Map.of("skillId", 29L, "info", "Phát triển và triển khai các ứng dụng với Docker."),
                Map.of("skillId", 30L, "info", "Quản lý và triển khai ứng dụng container với Kubernetes."),
                Map.of("skillId", 31L, "info", "Quản lý mã nguồn với Git."),
                Map.of("skillId", 32L, "info", "Thiết lập và quản lý các quy trình CI/CD để tự động hóa phát triển và triển khai."),
                Map.of("skillId", 33L, "info", "Quản lý và triển khai ứng dụng trên AWS (Amazon Web Services)."),
                Map.of("skillId", 34L, "info", "Quản lý và triển khai ứng dụng trên Azure."),
                Map.of("skillId", 35L, "info", "Quản lý hạ tầng và dịch vụ đám mây trên Google Cloud Platform (GCP)."),
                Map.of("skillId", 36L, "info", "Thiết lập và tự động hóa các quy trình triển khai với Jenkins."),
                Map.of("skillId", 37L, "info", "Triển khai hạ tầng dưới dạng mã với Terraform."),
                Map.of("skillId", 38L, "info", "Tự động hóa cấu hình và triển khai phần mềm với Ansible."),
                Map.of("skillId", 39L, "info", "Phân tích dữ liệu và trực quan hóa với Power BI."),
                Map.of("skillId", 40L, "info", "Trực quan hóa và phân tích dữ liệu với Tableau."),
                Map.of("skillId", 41L, "info", "Xử lý và phân tích dữ liệu lớn (Big Data)."),
                Map.of("skillId", 42L, "info", "Xây dựng và triển khai các mô hình học máy (ML)."),
                Map.of("skillId", 43L, "info", "Xây dựng các hệ thống học sâu (Deep Learning - DL)."),
                Map.of("skillId", 44L, "info", "Phân tích và xử lý ngôn ngữ tự nhiên (NLP)."),
                Map.of("skillId", 45L, "info", "Phát triển các mô hình học máy với TensorFlow (TF)."),
                Map.of("skillId", 46L, "info", "Xây dựng mô hình học máy với PyTorch."),
                Map.of("skillId", 47L, "info", "Áp dụng phương pháp Agile trong quản lý dự án phần mềm."),
                Map.of("skillId", 48L, "info", "Áp dụng phương pháp Scrum để quản lý và triển khai phần mềm."),
                Map.of("skillId", 49L, "info", "Thiết kế giao diện người dùng với Figma."),
                Map.of("skillId", 50L, "info", "Phát triển ứng dụng di động với Xamarin."),
                Map.of("skillId", 51L, "info", "Phát triển và triển khai các API với GraphQL.")
        );

        for (int i = 1; i <= 344; i++) {
            int sizeSkill = faker.random().nextInt(3, 10);
            Job job = jobRepository.findById((long) i).orElse(null);
            List<Long> skillId = new ArrayList<>();
            int j = 0;
            while (j < sizeSkill) {
                Map<String, Object> skill = skillAndInfos.get(faker.random().nextInt(skillAndInfos.size()));
                if (!skillId.contains(skill.get("skillId"))) {
                    skillId.add((Long) skill.get("skillId"));
                    String moreInfos = skill.get("info").toString();
                    SkillLevel skillLevel = skillLevels[faker.random().nextInt(skillLevels.length)];
                    Skill skillAdd = skillRepository.findById((Long) skill.get("skillId")).orElse(null);
                    JobSkill jobSkill = JobSkill.builder()
                            .moreInfos(moreInfos)
                            .skillLevel(skillLevel)
                            .skill(skillAdd)
                            .job(job)
                            .id(JobSkillId.builder().jobId((long) i).skillId((Long) skill.get("skillId")).build())
                            .build();
                    jobSkillRepository.saveAndFlush(jobSkill);
                    j++;
                }
            }
        }

    }

    @Test
    void insertDataCandidateSkill() {
        Faker faker = new Faker();
        SkillLevel[] skillLevels = SkillLevel.values();
        List<Map<String, Object>> skillAndInfos = List.of(
                Map.of("skillId", 1L, "info", "Lập trình phần mềm, ứng dụng web và hệ thống với Java."),
                Map.of("skillId", 2L, "info", "Phân tích dữ liệu và phát triển các ứng dụng khoa học dữ liệu với Python."),
                Map.of("skillId", 3L, "info", "Phát triển giao diện người dùng và ứng dụng web với JavaScript."),
                Map.of("skillId", 4L, "info", "Phát triển ứng dụng web với TypeScript, cải thiện chất lượng mã nguồn."),
                Map.of("skillId", 5L, "info", "Lập trình ứng dụng với C++, tối ưu hóa hiệu suất và hệ thống."),
                Map.of("skillId", 6L, "info", "Phát triển ứng dụng và phần mềm với C#, xây dựng hệ thống đa nền tảng."),
                Map.of("skillId", 7L, "info", "Phát triển web với PHP, tích hợp cơ sở dữ liệu và hệ thống."),
                Map.of("skillId", 8L, "info", "Xây dựng ứng dụng web và phần mềm với Ruby."),
                Map.of("skillId", 9L, "info", "Lập trình với Go, xây dựng các ứng dụng có hiệu suất cao."),
                Map.of("skillId", 10L, "info", "Phát triển ứng dụng di động và phần mềm với Swift."),
                Map.of("skillId", 11L, "info", "Xây dựng ứng dụng di động với Kotlin cho Android."),
                Map.of("skillId", 12L, "info", "Phát triển các ứng dụng hiệu suất cao và các công cụ hệ thống với Rust."),
                Map.of("skillId", 13L, "info", "Xây dựng giao diện web tĩnh và động với HTML."),
                Map.of("skillId", 14L, "info", "Thiết kế và bố trí giao diện web với CSS."),
                Map.of("skillId", 15L, "info", "Phát triển giao diện người dùng với React, tạo ứng dụng web động."),
                Map.of("skillId", 16L, "info", "Phát triển ứng dụng web với Vue, xây dựng giao diện người dùng tương tác."),
                Map.of("skillId", 17L, "info", "Xây dựng các ứng dụng đơn trang (SPA) với Angular."),
                Map.of("skillId", 18L, "info", "Phát triển ứng dụng web với Next.js, tối ưu hóa hiệu suất."),
                Map.of("skillId", 19L, "info", "Lập trình và phát triển ứng dụng backend với Node.js."),
                Map.of("skillId", 20L, "info", "Xây dựng ứng dụng web nhanh chóng với Express.js."),
                Map.of("skillId", 21L, "info", "Phát triển các ứng dụng web Java với Spring Framework."),
                Map.of("skillId", 22L, "info", "Xây dựng ứng dụng web mạnh mẽ với Django."),
                Map.of("skillId", 23L, "info", "Phát triển ứng dụng web với Flask, một framework Python nhẹ."),
                Map.of("skillId", 24L, "info", "Phát triển ứng dụng di động với Flutter."),
                Map.of("skillId", 25L, "info", "Quản lý cơ sở dữ liệu quan hệ với MySQL."),
                Map.of("skillId", 26L, "info", "Quản lý và phát triển cơ sở dữ liệu với PostgreSQL."),
                Map.of("skillId", 27L, "info", "Lưu trữ và truy vấn dữ liệu không cấu trúc với MongoDB."),
                Map.of("skillId", 28L, "info", "Lưu trữ và truy xuất dữ liệu nhanh chóng với Redis."),
                Map.of("skillId", 29L, "info", "Phát triển và triển khai các ứng dụng với Docker."),
                Map.of("skillId", 30L, "info", "Quản lý và triển khai ứng dụng container với Kubernetes."),
                Map.of("skillId", 31L, "info", "Quản lý mã nguồn với Git."),
                Map.of("skillId", 32L, "info", "Thiết lập và quản lý các quy trình CI/CD để tự động hóa phát triển và triển khai."),
                Map.of("skillId", 33L, "info", "Quản lý và triển khai ứng dụng trên AWS (Amazon Web Services)."),
                Map.of("skillId", 34L, "info", "Quản lý và triển khai ứng dụng trên Azure."),
                Map.of("skillId", 35L, "info", "Quản lý hạ tầng và dịch vụ đám mây trên Google Cloud Platform (GCP)."),
                Map.of("skillId", 36L, "info", "Thiết lập và tự động hóa các quy trình triển khai với Jenkins."),
                Map.of("skillId", 37L, "info", "Triển khai hạ tầng dưới dạng mã với Terraform."),
                Map.of("skillId", 38L, "info", "Tự động hóa cấu hình và triển khai phần mềm với Ansible."),
                Map.of("skillId", 39L, "info", "Phân tích dữ liệu và trực quan hóa với Power BI."),
                Map.of("skillId", 40L, "info", "Trực quan hóa và phân tích dữ liệu với Tableau."),
                Map.of("skillId", 41L, "info", "Xử lý và phân tích dữ liệu lớn (Big Data)."),
                Map.of("skillId", 42L, "info", "Xây dựng và triển khai các mô hình học máy (ML)."),
                Map.of("skillId", 43L, "info", "Xây dựng các hệ thống học sâu (Deep Learning - DL)."),
                Map.of("skillId", 44L, "info", "Phân tích và xử lý ngôn ngữ tự nhiên (NLP)."),
                Map.of("skillId", 45L, "info", "Phát triển các mô hình học máy với TensorFlow (TF)."),
                Map.of("skillId", 46L, "info", "Xây dựng mô hình học máy với PyTorch."),
                Map.of("skillId", 47L, "info", "Áp dụng phương pháp Agile trong quản lý dự án phần mềm."),
                Map.of("skillId", 48L, "info", "Áp dụng phương pháp Scrum để quản lý và triển khai phần mềm."),
                Map.of("skillId", 49L, "info", "Thiết kế giao diện người dùng với Figma."),
                Map.of("skillId", 50L, "info", "Phát triển ứng dụng di động với Xamarin."),
                Map.of("skillId", 51L, "info", "Phát triển và triển khai các API với GraphQL.")
        );

        for (int i = 51; i <= 100; i++) {
            int sizeSkill = faker.random().nextInt(5, 15);
            Candidate candidate = candidateRepository.findById((long) i).orElse(null);
            List<Long> skillId = new ArrayList<>();
            int j = 0;
            while (j < sizeSkill) {
                Map<String, Object> skill = skillAndInfos.get(faker.random().nextInt(skillAndInfos.size()));
                if (!skillId.contains(skill.get("skillId"))) {
                    skillId.add((Long) skill.get("skillId"));
                    String moreInfos = skill.get("info").toString();
                    SkillLevel skillLevel = skillLevels[faker.random().nextInt(skillLevels.length)];
                    Skill skillAdd = skillRepository.findById((Long) skill.get("skillId")).orElse(null);
                    CandidateSkill candidateSkill = CandidateSkill.builder()
                            .moreInfos(moreInfos)
                            .skillLevel(skillLevel)
                            .skill(skillAdd)
                            .candidate(candidate)
                            .id(CandidateSkillId.builder().canId(candidate.getId())
                                    .skillId(skillAdd.getId()).build())
                            .build();
                    candidateSkillRepository.saveAndFlush(candidateSkill);
                    j++;
                }
            }
        }
    }

}
