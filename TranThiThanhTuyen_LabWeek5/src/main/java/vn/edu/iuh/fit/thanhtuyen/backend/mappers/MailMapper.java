package vn.edu.iuh.fit.thanhtuyen.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Mail;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {JobMapper.class, CandidateMapper.class, UserMapper.class})
public interface MailMapper {
    @Mapping(target = "candidate.id", source = "candidateId")
    @Mapping(target = "job.id", source = "jobId")
    Mail toEntity(MailDto mailDto);

    @Mapping(target = "candidateId", source = "candidate.id")
    @Mapping(target = "jobId", source = "job.id")
    MailDto toDto(Mail mail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mail partialUpdate(MailDto mailDto, @MappingTarget Mail mail);
}