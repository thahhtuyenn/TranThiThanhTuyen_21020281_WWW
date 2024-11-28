package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;

public interface CompanyModel {
    CompanyDto getCompanyById(Long id);
}
