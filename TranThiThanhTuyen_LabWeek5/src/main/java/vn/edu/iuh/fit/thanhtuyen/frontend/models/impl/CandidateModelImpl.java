package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;

@Component
public class CandidateModelImpl implements CandidateModel {
    private static final String API_URL = "http://localhost:9623/api/candidates";
    private final RestTemplate restTemplate = new RestTemplate();

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

    @Override
    public CandidateDto getCandidateById(Long id) {
        ResponseEntity<CandidateDto> response = restTemplate.exchange(
                API_URL + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    @Override
    public CandidateDto saveCandidate(CandidateDto candidateDto) {
        ResponseEntity<CandidateDto> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                new HttpEntity<>(candidateDto),
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    @Override
    public boolean removeCandidateSkill(Long candidateId, Long skillId) {
        String url = API_URL + "/remove-candidate-skill?candidateId=" + candidateId + "&skillId=" + skillId;
        ResponseEntity<Boolean> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return Boolean.TRUE.equals(response.getBody());
    }

    @Override
    public CandidateSkillDto saveCandidateSkill(CandidateSkillDto candidateSkillDto) {
        String url = API_URL + "/update-candidate-skill";
        ResponseEntity<CandidateSkillDto> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(candidateSkillDto),
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
}
