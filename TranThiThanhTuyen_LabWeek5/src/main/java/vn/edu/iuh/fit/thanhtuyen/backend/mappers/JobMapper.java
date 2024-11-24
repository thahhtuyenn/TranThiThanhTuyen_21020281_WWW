package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Job;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {JobSkillMapper.class, CompanyMapper.class}
)
public interface JobMapper {
    Job toEntity(JobDto jobDto);

    @AfterMapping
    default void linkJobSkills(@MappingTarget Job job) {
        if (job.getJobSkills() != null) {
            job.getJobSkills().forEach(jobSkill -> jobSkill.setJob(job));
        }
    }

    JobDto toDto(Job job);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Job partialUpdate(JobDto jobDto, @MappingTarget Job job);
}