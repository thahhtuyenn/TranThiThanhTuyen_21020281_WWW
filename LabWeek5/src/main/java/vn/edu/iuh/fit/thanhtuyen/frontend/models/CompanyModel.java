package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;

import java.util.List;

public interface CompanyModel {
    List<CompanyDto> getAllCompany();
    CompanyDto getCompanyById(Long id);
    boolean insertCompany(CompanyDto companyDto);
    CompanyDto getCompanyByEmailAndPhone(String email, String phone);
}
