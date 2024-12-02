package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.CompanyMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.MailMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Job;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Mail;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private MailMapper mailMapper;

    @Override
    public CompanyDto getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(companyMapper::toDto).orElse(new CompanyDto());
    }

    @Override
    public CompanyDto saveCompany(CompanyDto company) {
        Company entity = companyMapper.toEntity(company);
        Company entityOld = companyRepository.findById(company.getId()).orElse(new Company());
        if (entity.getId() != null){
            entity = companyMapper.partialUpdate(company, entityOld);
        }
        CountryCode countryCode = entityOld.getAddress().getCountry();
        entity.getAddress().setCountry(countryCode);
        entity = companyRepository.saveAndFlush(entity);
        return companyMapper.toDto(entity);
    }

    @Override
    public List<MailDto> getMailByCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null){
            return null;
        }
        List<Job> jobs = company.getJobs();
        List<Mail> mails = new ArrayList<>();
        for (Job job : jobs) {
            mails.addAll(job.getMails());
        }
        return mails.stream().map(mailMapper::toDto).collect(Collectors.toList());
    }
}
