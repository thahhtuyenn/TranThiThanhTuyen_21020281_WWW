package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.CompanyMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CompanyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getAllCompany() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(companyMapper::toDto).orElse(new CompanyDto());
    }

    @Override
    public boolean saveCompany(CompanyDto company) {
        Company companyEntity = companyMapper.toEntity(company);
        companyRepository.save(companyEntity);
        return true;
    }

    @Override
    public CompanyDto findByEmail(String email) {
        Company company = companyRepository.findByEmail(email);
        return companyMapper.toDto(company);
    }
}
