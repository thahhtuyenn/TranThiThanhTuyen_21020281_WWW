package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.utils.AppUtil;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

@Component
public class JobModelImpl implements JobModel {
    private static final String API_URL = "http://localhost:9623/api/jobs";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public JobDto saveJob(JobDto jobDto) {
        ResponseEntity<JobDto> response = restTemplate.exchange(
                AppUtil.JOBS_API, HttpMethod.POST, new HttpEntity<>(jobDto),
                new ParameterizedTypeReference<JobDto>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getAllJobsAsPaging(int page, int size) {
        String url = API_URL + "?page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public JobDto getJobById(Long id) {
        String url = API_URL + "/" + id;
        ResponseEntity<JobDto> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<JobDto>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsByName(String jobName, int page, int size) {
        String url = API_URL + "/searchByJobName?jobName=" + jobName + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsSuitableForCandidate(Long candidateId, int per, int page, int size) {
        String url = API_URL + "/jobsSuitableForCandidate?candidateId=" + candidateId + "&per=" + per + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsByCompany(Long companyId, int page, int size) {
        String url = API_URL + "/getJobsByCompany?companyId=" + companyId + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsByCompanyAndJobName(Long companyId, String jobName, int page, int size) {
        String url = API_URL + "/searchJobsOfCompany?companyId=" + companyId + "&jobName=" + jobName + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public boolean removeJobSkill(Long jobId, Long skillId) {
        String url = API_URL + "/remove-job-skill?jobId=" + jobId + "&skillId=" + skillId;
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Boolean>() {
                });
        return Boolean.TRUE.equals(response.getBody());
    }

    @Override
    public JobSkillDto addJobSkill(JobSkillDto jobSkillDto) {
        String url = API_URL + "/add-job-skill";
        ResponseEntity<JobSkillDto> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(jobSkillDto),
                new ParameterizedTypeReference<JobSkillDto>() {
                });
        return response.getBody();
    }
}
