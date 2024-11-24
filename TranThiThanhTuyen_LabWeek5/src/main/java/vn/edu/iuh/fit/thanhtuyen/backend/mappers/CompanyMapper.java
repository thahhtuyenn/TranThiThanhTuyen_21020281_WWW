package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, JobMapper.class, AddressMapper.class})
public interface CompanyMapper {
    Company toEntity(CompanyDto companyDto);

    CompanyDto toDto(Company company);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Company partialUpdate(CompanyDto companyDto, @MappingTarget Company company);
}