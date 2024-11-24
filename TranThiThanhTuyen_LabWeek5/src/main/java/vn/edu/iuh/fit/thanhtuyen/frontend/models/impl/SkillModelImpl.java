package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.SkillModel;

import java.util.List;

@Component
public class SkillModelImpl implements SkillModel {
    private static final String API_URL = "http://localhost:9623/api/skills";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<SkillDto> getAllSkills() {
        ResponseEntity<List<SkillDto>> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    @Override
    public SkillDto getSkillById(Long id) {
        ResponseEntity<SkillDto> response = restTemplate.exchange(
                API_URL + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
