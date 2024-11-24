package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Skill;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {
    Skill toEntity(SkillDto skillDto);

    SkillDto toDto(Skill skill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Skill partialUpdate(SkillDto skillDto, @MappingTarget Skill skill);
}