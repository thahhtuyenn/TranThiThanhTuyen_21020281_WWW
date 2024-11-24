package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.RoleDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleDto roleDto);

    RoleDto toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDto roleDto, @MappingTarget Role role);
}