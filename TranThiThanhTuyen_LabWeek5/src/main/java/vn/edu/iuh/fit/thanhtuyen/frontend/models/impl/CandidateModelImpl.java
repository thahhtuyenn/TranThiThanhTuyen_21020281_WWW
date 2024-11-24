package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;

@Component
public class CandidateModelImpl implements CandidateModel {
    private static final String API_URL = "http://localhost:9623/api/candidates";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public CandidateDto getCandidateByEmail(String email) {
        ResponseEntity<CandidateDto> response = restTemplate.exchange(
                API_URL + "?email=" + email,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    @Override
    public PageDTO<CandidateDto> getCandidateSuitableForJob(Long jobId, int per, int page, int size) {
        ResponseEntity<PageDTO<CandidateDto>> response = restTemplate.exchange(
                API_URL + "/getCandidatesSuitableForJob?jobId=" + jobId + "&per=" + per + "&page=" + page + "&size=" + size,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
}
