package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;

import java.util.List;

public interface CompanyModel {
    CompanyDto getCompanyById(Long id);
    CompanyDto saveCompany(CompanyDto company);
    boolean sendMail(Long jobId, Long candidateId);
    List<MailDto> getMailsByCompanyId(Long companyId);
}
