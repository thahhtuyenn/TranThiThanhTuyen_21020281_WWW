package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

public interface JobModel {
    JobDto saveJob(JobDto jobDto);
    PageDTO<JobDto> getAllJobsAsPaging(int page, int size);
    JobDto getJobById(Long id);
    PageDTO<JobDto> getJobsByName(String jobName, int page, int size);
    PageDTO<JobDto> getJobsSuitableForCandidate(Long candidateId, int per, int page, int size);
    PageDTO<JobDto> getJobsByCompany(Long companyId, int page, int size);
    PageDTO<JobDto> getJobsByCompanyAndJobName(Long companyId, String jobName, int page, int size);
}
