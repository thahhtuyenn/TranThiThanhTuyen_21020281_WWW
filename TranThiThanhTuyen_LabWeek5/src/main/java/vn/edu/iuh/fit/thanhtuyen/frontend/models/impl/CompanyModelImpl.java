package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
    public CompanyDto getCompanyById(Long id) {
        String url = API_URL + "/" + id;
        ResponseEntity<CompanyDto> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CompanyDto>() {
        });
        return response.getBody();
    }

    @Override
    public CompanyDto saveCompany(CompanyDto company) {
        ResponseEntity<CompanyDto> response = restTemplate.exchange(
                API_URL, HttpMethod.POST, new HttpEntity<>(company),
                new ParameterizedTypeReference<CompanyDto>() {
        });
        return response.getBody();
    }
}
