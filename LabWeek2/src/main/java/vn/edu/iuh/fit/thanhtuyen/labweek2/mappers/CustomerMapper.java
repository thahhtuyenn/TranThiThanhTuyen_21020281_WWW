package vn.edu.iuh.fit.thanhtuyen.labweek2.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Customer;

@Mapper
public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerDto customerDto, @MappingTarget Customer customer);
}