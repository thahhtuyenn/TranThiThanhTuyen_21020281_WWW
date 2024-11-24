package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.CandidateSkill;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CandidateSkillMapper {

    @Mapping(target = "id.skillId", source = "skillId")
    @Mapping(target = "id.canId", source = "canId")
    CandidateSkill toEntity(CandidateSkillDto candidateSkillDto);

    @Mapping(target = "skillId", source = "id.skillId")
    @Mapping(target = "canId", source = "id.canId")
    CandidateSkillDto toDto(CandidateSkill candidateSkill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CandidateSkill partialUpdate(CandidateSkillDto candidateSkillDto, @MappingTarget CandidateSkill candidateSkill);
}