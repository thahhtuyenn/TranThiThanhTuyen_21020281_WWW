package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompany();
    CompanyDto getCompanyById(Long id);
    boolean saveCompany(CompanyDto company);
    CompanyDto findByEmailAndPhone(String email, String phone);
}
