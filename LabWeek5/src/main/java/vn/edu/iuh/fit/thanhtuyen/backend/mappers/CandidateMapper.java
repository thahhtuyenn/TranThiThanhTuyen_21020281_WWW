package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CandidateSkillMapper.class, ExperienceMapper.class}
)
public interface CandidateMapper {
    Candidate toEntity(CandidateDto candidateDto);

    @AfterMapping
    default void linkCandidateSkills(@MappingTarget Candidate candidate) {
        if (candidate.getCandidateSkills() != null){
            candidate.getCandidateSkills().forEach(candidateSkill -> candidateSkill.setCandidate(candidate));
        }
    }

    @AfterMapping
    default void linkExperiences(@MappingTarget Candidate candidate) {
       if (candidate.getExperiences() != null){
           candidate.getExperiences().forEach(experience -> experience.setCandidate(candidate));
       }
    }

    CandidateDto toDto(Candidate candidate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Candidate partialUpdate(CandidateDto candidateDto, @MappingTarget Candidate candidate);
}