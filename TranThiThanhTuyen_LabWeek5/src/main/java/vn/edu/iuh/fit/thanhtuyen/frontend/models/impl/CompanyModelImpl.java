package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;

@Component
public class CompanyModelImpl implements CompanyModel {
    private static final String API_URL = "http://localhost:9623/api/companies";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public CompanyDto getCompanyByEmail(String email) {
        ResponseEntity<CompanyDto> response = restTemplate.exchange(
                API_URL + "?email=" + email,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
