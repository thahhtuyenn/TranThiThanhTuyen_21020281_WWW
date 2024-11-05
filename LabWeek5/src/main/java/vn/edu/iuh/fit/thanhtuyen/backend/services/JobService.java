package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;

import java.util.List;

public interface JobService {
    List<JobDto> getAllJobs();
    JobDto getJobById(Long id);
    List<JobDto> getJobsByCityAndStreetAndExperienceNumberAndJobName(String city, String street, int experienceNumber, String jobName);
}
