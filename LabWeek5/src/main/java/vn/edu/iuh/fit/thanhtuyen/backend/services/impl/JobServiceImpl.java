package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.JobMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Job;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.JobRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.JobService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;


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
}
