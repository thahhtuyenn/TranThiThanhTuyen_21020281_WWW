package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.JobSkill;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JobSkillMapper {
    @Mapping(target = "id.skillId", source = "skillId")
    @Mapping(target = "id.jobId", source = "jobId")
    JobSkill toEntity(JobSkillDto jobSkillDto);

    @Mapping(target = "skillId", source = "id.skillId")
    @Mapping(target = "jobId", source = "id.jobId")
    JobSkillDto toDto(JobSkill jobSkill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JobSkill partialUpdate(JobSkillDto jobSkillDto, @MappingTarget JobSkill jobSkill);
}