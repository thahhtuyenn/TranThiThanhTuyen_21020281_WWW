package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.ExperienceDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Experience;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExperienceMapper {
    Experience toEntity(ExperienceDto experienceDto);

    ExperienceDto toDto(Experience experience);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Experience partialUpdate(ExperienceDto experienceDto, @MappingTarget Experience experience);
}