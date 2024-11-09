package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

import java.util.List;

public interface CompanyModel {
    PageDTO<CompanyDto> getAllCompany();
    CompanyDto getCompanyById(Long id);
    boolean insertCompany(CompanyDto companyDto);
    CompanyDto getCompanyByEmailAndPhone(String email, String phone);
}
