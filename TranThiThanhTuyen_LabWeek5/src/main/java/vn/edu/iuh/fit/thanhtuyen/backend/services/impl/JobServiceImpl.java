package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.JobMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.JobSkillMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.*;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.*;
import vn.edu.iuh.fit.thanhtuyen.backend.services.JobService;
import vn.edu.iuh.fit.thanhtuyen.backend.services.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private JobSkillMapper jobSkillMapper;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private MailRepository mailRepository;

    @Override
    public JobDto getJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        return job.map(jobMapper::toDto).orElse(null);
    }

    @Override
    public PageDTO<JobDto> getJobsByName(String name, int page, int size) {
        Page<Job> jobs = jobRepository.findByJobName(name, PageRequest.of(page, size));
        PageDTO<JobDto> pageDTO = new PageDTO<>();
        List<JobDto> jobDtos = jobs.stream().map(jobMapper::toDto).collect(Collectors.toList());
        pageDTO.setContent(jobDtos);
        pageDTO.setPage(jobs.getNumber());
        pageDTO.setSize(jobs.getSize());
        pageDTO.setTotalPages(jobs.getTotalPages());
        return pageDTO;
    }


    @Override
    public PageDTO<JobDto> getJobsPaging(int page, int size) {
        Page<Job> jobs = jobRepository.findAll(PageRequest.of(page, size));
        PageDTO<JobDto> pageDTO = new PageDTO<>();
        List<JobDto> jobDtos = jobs.stream().map(jobMapper::toDto).collect(Collectors.toList());
        pageDTO.setContent(jobDtos);
        pageDTO.setPage(jobs.getNumber());
        pageDTO.setSize(jobs.getSize());
        pageDTO.setTotalPages(jobs.getTotalPages());
        return pageDTO;
    }

    @Override
    public PageDTO<JobDto> getJobsMatchingCandidate(Long candidateId, int per, int page, int size) {
        Page<Job> jobs = jobRepository.findJobsForCandidateWithLevel(candidateId, per, PageRequest.of(page, size));
        PageDTO<JobDto> pageDTO = new PageDTO<>();
        List<JobDto> jobDtos = jobs.stream().map(jobMapper::toDto).collect(Collectors.toList());
        pageDTO.setContent(jobDtos);
        pageDTO.setPage(jobs.getNumber());
        pageDTO.setSize(jobs.getSize());
        pageDTO.setTotalPages(jobs.getTotalPages());
        return pageDTO;
    }

    @Override
    public PageDTO<JobDto> getJobsByCompanyId(Long companyId, int page, int size) {
        Page<Job> jobs = jobRepository.findByCompanyId(companyId, PageRequest.of(page, size));
        PageDTO<JobDto> pageDTO = new PageDTO<>();
        List<JobDto> jobDtos = jobs.stream().map(jobMapper::toDto).collect(Collectors.toList());
        pageDTO.setContent(jobDtos);
        pageDTO.setPage(jobs.getNumber());
        pageDTO.setSize(jobs.getSize());
        pageDTO.setTotalPages(jobs.getTotalPages());
        return pageDTO;
    }

    @Override
    public PageDTO<JobDto> getJobsByCompanyAndName(Long companyId, String name, int page, int size) {
        Page<Job> jobs = jobRepository.findByCompanyAndName(companyId, name, PageRequest.of(page, size));
        PageDTO<JobDto> pageDTO = new PageDTO<>();
        List<JobDto> jobDtos = jobs.stream().map(jobMapper::toDto).collect(Collectors.toList());
        pageDTO.setContent(jobDtos);
        pageDTO.setPage(jobs.getNumber());
        pageDTO.setSize(jobs.getSize());
        pageDTO.setTotalPages(jobs.getTotalPages());
        return pageDTO;
    }

    @Override
    public JobDto saveJob(JobDto jobDto) {
        List<JobSkillDto> jobSkillDtos = jobDto.getJobSkills();
        Job job = jobMapper.toEntity(jobDto);
        if (job.getId() != null){
            Job jobOld = jobRepository.findById(job.getId()).orElse(null);
            job = jobMapper.partialUpdate(jobDto, jobOld);
        }
        job.setJobSkills(new ArrayList<>());
        job = jobRepository.saveAndFlush(job);

        for (JobSkillDto jobSkillDto : jobSkillDtos) {
            JobSkill jobSkill = jobSkillMapper.toEntity(jobSkillDto);
            Skill skill = skillRepository.findById(jobSkillDto.getSkillId()).orElse(null);
            jobSkill.setJob(job);
            jobSkill.getId().setJobId(job.getId());
            jobSkill.setSkill(skill);
            jobSkillRepository.saveAndFlush(jobSkill);
        }
        return jobMapper.toDto(job);
    }

    @Override
    @Transactional
    public boolean removeJobSkill(Long jobId, Long skillId) {
        Optional<JobSkill> jobSkill = jobSkillRepository.findByJobIdAndSkillId(jobId, skillId);
        if (jobSkill.isPresent()) {
            jobSkillRepository.removeByJobIdAndSkillId(jobId, skillId);
            return true;
        }
        return false;
    }

    @Override
    public JobSkillDto addJobSkill(JobSkillDto jobSkillDto) {
        Job job = jobRepository.findById(jobSkillDto.getJobId()).orElse(null);
        JobSkill jobSkill = jobSkillMapper.toEntity(jobSkillDto);
        if(job != null){
            Skill skill = skillRepository.findById(jobSkillDto.getSkillId()).orElse(null);
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkill = jobSkillRepository.saveAndFlush(jobSkill);
        }
        return jobSkillMapper.toDto(jobSkill);
    }

    @Override
    public boolean sendMail(MailDto mailDto) {
        Job job = jobRepository.findById(mailDto.getJobId()).orElse(null);
        Candidate candidate = candidateRepository.findById(mailDto.getCandidateId()).orElse(null);
        if (job == null || candidate == null) {
            return false;
        }
        // content email
        StringBuilder contentBuilder = new StringBuilder()
                .append(String.format("<p>Kính gửi %s,</p>", candidate.getFullName()))
                .append(String.format("<p>Chúng tôi rất vui mừng được thông báo rằng bạn đã được chọn để tiếp cận cơ hội ứng tuyển vào vị trí <strong>%s</strong> tại <strong>%s</strong>.</p>", job.getJobName(), job.getCompany().getCompName()))
                .append("<p>Sau khi xem xét hồ sơ của bạn, chúng tôi tin rằng bạn sở hữu những kỹ năng và kinh nghiệm phù hợp với yêu cầu công việc của chúng tôi. <strong>" + job.getCompany().getCompName() + "</strong> là một môi trường làm việc năng động và đầy thử thách, nơi bạn có thể phát triển sự nghiệp của mình và đóng góp vào sự thành công chung.</p>")
                .append("<p>Thông tin chi tiết về vị trí tuyển dụng:</p>")
                .append("<ul>")
                .append(String.format("<li><strong>Vị trí:</strong> %s</li>", job.getJobName()))
                .append("</ul>")
                .append(String.format("<p>Chúng tôi rất mong muốn được làm việc cùng bạn và hy vọng bạn sẽ tham gia vào đội ngũ của chúng tôi. Nếu bạn quan tâm và muốn tiếp tục quy trình tuyển dụng, vui lòng trả lời email này hoặc đăng ký tham gia phỏng vấn qua <a href='mailto:%s'>%s</a>.</p>", job.getCompany().getEmail(), job.getCompany().getEmail()))
                .append(String.format("<p>Nếu bạn có bất kỳ câu hỏi nào, đừng ngần ngại liên hệ với chúng tôi qua địa chỉ email này hoặc số điện thoại <a href='tel:%s'>%s</a>.</p>", job.getCompany().getPhone(), job.getCompany().getPhone()))
                .append(String.format("<p>Cảm ơn bạn đã quan tâm đến cơ hội nghề nghiệp tại <strong>%s</strong>. Chúng tôi mong được sớm nhận được phản hồi từ bạn.</p>", job.getCompany().getCompName()))
                .append("<p>Trân trọng,</p>")
                .append(String.format("<p><a href='%s'>%s</a></p>", job.getCompany().getWebUrl(), job.getCompany().getCompName()));
        // subject: Lời Mời Ứng Tuyển Vị Trí [Tên Vị Trí] tại [Tên Công Ty]
        String subject = String.format("Lời Mời Ứng Tuyển Vị Trí %s tại %s", job.getJobName(), job.getCompany().getCompName());
        try {
            if(mailDto.getTo() == null || mailDto.getTo().isEmpty()){
                mailDto.setTo(candidate.getEmail());
            }
            mailService.senderForCandidate(job.getCompany(), contentBuilder.toString(), mailDto.getTo(), subject);

            Mail mail = Mail.builder()
                    .content(contentBuilder.toString())
                    .candidate(candidate)
                    .to(mailDto.getTo())
                    .subject(subject)
                    .job(job)
                    .build();

            mailRepository.save(mail);
            return true;
        } catch (Exception e) {
            log.error("Error when send mail to candidate", e);
        }
        return false;
    }
}
