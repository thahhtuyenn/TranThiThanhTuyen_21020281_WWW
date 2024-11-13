package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

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
    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()) {
            return new ArrayList<>();
        }
        return jobs.stream().map(jobMapper::toDto).collect(Collectors.toList());
    }

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
    public int countPageJobs(int size) {
        return jobRepository.countPageJobs(size);
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
//        //get jobSkills from jobDto
//        List<JobSkillDto> jobSkillDtos = jobDto.getJobSkills();
//        //set job skillDto = new ArrayList to avoid null pointer exception
//        jobDto.setJobSkills(new ArrayList<>());
//        if (jobDto.getId() == null || jobDto.getId() == 0) {
//            jobDto.setId(0L);
//        }
//        //convert jobDto to job
//        Job job = jobMapper.toEntity(jobDto);
//        //insert job
//        job = jobRepository.save(job);
//        //delete jobSkills old by jobId if job has jobSkills not empty
////        List<JobSkill> jobSkillsOld = jobSkillRepository.findAll();
//        if (jobSkillRepository.existsByJobId(job.getId())) {
//            jobSkillRepository.deleteByJobId(job.getId());
//        }
//
//        //after insert job
//        //set job for jobSkillDTOs
//        for (JobSkillDto jobSkillDto : jobSkillDtos) {
//           jobSkillDto.setJobId(job.getId());
//
//        }
//        //convert jobSkillDTOs to jobSkills
//        List<JobSkill> jobSkills = jobSkillDtos.stream().map(jobSkillMapper::toEntity).collect(Collectors.toList());
//        List<JobSkill> jobSkillsTemp = new ArrayList<>();
//        //insert jobSkills to database
//        for (JobSkill jobSkill : jobSkills) {
//            jobSkill.setJob(job);
//            jobSkill = jobSkillRepository.saveAndFlush(jobSkill);
//            jobSkillsTemp.add(jobSkill);
//        }
//        //set jobSkills for job
//
//        job.setJobSkills(jobSkillsTemp);
//
//        job = jobRepository.save(job);
//        return jobMapper.toDto(job);


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
}
