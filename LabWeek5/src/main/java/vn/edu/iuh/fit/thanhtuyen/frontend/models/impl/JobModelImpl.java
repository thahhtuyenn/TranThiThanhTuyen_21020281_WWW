package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.resources.JobResource;
import vn.edu.iuh.fit.thanhtuyen.backend.utils.AppUtil;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

import java.util.List;

@Component
public class JobModelImpl implements JobModel {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<JobDto> getAllJobs() {
        ResponseEntity<List<JobDto>> response = restTemplate.exchange(AppUtil.JOBS_API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsPaging(int page, int size) {
        String url = AppUtil.JOBS_API + "/paging?page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public List<JobDto> getJobsByCityAndJobName(String city, String jobName) {
        String url = AppUtil.JOBS_API + "/search?city=" + city + "&jobName=" + jobName;
        ResponseEntity<List<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public JobDto getJobById(Long id) {
        String url = AppUtil.JOBS_API + "/id?id=" + id;
        ResponseEntity<JobDto> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<JobDto>() {
                });
        return response.getBody();
    }

    @Override
    public int countPageOfJobs(int size) {
        String url = AppUtil.JOBS_API + "/countPage?size=" + size;
        ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Integer>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsMatchingWithCandidate(Long candidateId, int per, int page, int size) {
        String url = AppUtil.JOBS_API + "/jobsMatchingCandidate?candidateId=" + candidateId +  "&per=" + per + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsByJobName(String jobName, int page, int size) {
        String url = AppUtil.JOBS_API + "/search?jobName=" + jobName + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody() ;
    }

    @Override
    public PageDTO<JobDto> getJobsByCompanyId(Long companyId, int page, int size) {
        String url = AppUtil.JOBS_API + "/jobsOfCompany?companyId=" + companyId + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }

    @Override
    public PageDTO<JobDto> getJobsByCompanyAndJobName(Long companyId, String job, int page, int size) {
        String url = AppUtil.JOBS_API + "/searchForCompany?companyId=" + companyId + "&jobName=" + job + "&page=" + page + "&size=" + size;
        ResponseEntity<PageDTO<JobDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageDTO<JobDto>>() {
                });
        return response.getBody();
    }
}
