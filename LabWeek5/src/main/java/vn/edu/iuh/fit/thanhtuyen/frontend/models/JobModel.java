package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

import java.util.List;

public interface JobModel {
    List<JobDto> getAllJobs();
    PageDTO<JobDto> getJobsPaging(int page, int size);
    List<JobDto> getJobsByCityAndJobName(String city, String jobName);
    JobDto getJobById(Long id);
    int countPageOfJobs(int size);
    PageDTO<JobDto> getJobsMatchingWithCandidate(Long candidateId, int per, int page, int size);
    PageDTO<JobDto> getJobsByJobName(String jobName, int page, int size);
    PageDTO<JobDto> getJobsByCompanyId(Long companyId, int page, int size);
    PageDTO<JobDto> getJobsByCompanyAndJobName(Long companyId, String job, int page, int size);

}
