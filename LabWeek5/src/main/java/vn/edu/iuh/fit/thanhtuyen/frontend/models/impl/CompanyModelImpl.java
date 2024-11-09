package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.resources.CompanyResource;
import vn.edu.iuh.fit.thanhtuyen.backend.utils.AppUtil;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;

import java.util.List;

@Component
public class CompanyModelImpl implements CompanyModel {

    private final RestTemplate restTemplate = new RestTemplate();
    private final CompanyResource companyResource = new CompanyResource();

    @Override
    public PageDTO<CompanyDto> getAllCompany() {
        return null;
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        ResponseEntity<CompanyDto> response = restTemplate.exchange(AppUtil.COMPANIES_API, HttpMethod.GET, null, CompanyDto.class);
        return response.getBody();
    }

    @Override
    public boolean insertCompany(CompanyDto companyDto) {
        return false;
    }

    @Override
    public CompanyDto getCompanyByEmailAndPhone(String email, String phone) {
        String url = AppUtil.COMPANIES_API + "/companyLogin?email=" + email + "&phone=" + phone;
        ResponseEntity<CompanyDto> response = restTemplate.exchange(url, HttpMethod.GET, null, CompanyDto.class);
        return response.getBody();
    }
}
