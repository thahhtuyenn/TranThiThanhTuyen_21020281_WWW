package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.JobMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.JobSkillMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.*;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.JobRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.JobService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Company company = companyRepository.findById(jobDto.getCompany().getId()).orElse(null);
        if (company == null) {
            return null;
        }


        Job job = jobMapper.toEntity(jobDto);
        job.setCompany(company);

        Job finalJob = job;
        List<JobSkill> jobSkills = job.getJobSkills().stream().map((jobSkill) -> {
            Skill skill = skillRepository.findById(jobSkill.getSkill().getId()).orElse(null);

            if(skill == null) return null;

            return JobSkill.builder()
                    .skillLevel(jobSkill.getSkillLevel())
                    .job(finalJob)
                    .moreInfos(jobSkill.getMoreInfos())
                    .skill(skill)
                    .id(
                            JobSkillId.builder()
                                    .jobId(finalJob.getId())
                                    .skillId(skill.getId())
                                    .build()
                    ).build();
        }).filter(Objects::nonNull).collect(Collectors.toList());

        job.setJobSkills(jobSkills);
        job = jobRepository.saveAndFlush(job);
        job = jobRepository.findById(job.getId()).orElse(null);

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
}
