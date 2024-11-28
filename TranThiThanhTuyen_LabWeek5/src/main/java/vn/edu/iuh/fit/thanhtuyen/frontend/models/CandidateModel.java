package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import org.springframework.data.domain.Page;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

public interface CandidateModel {
    PageDTO<CandidateDto> getCandidateSuitableForJob(Long jobId, int per, int page, int size);
    CandidateDto getCandidateById(Long id);
    CandidateDto saveCandidate(CandidateDto candidateDto);
    boolean removeCandidateSkill(Long candidateId, Long skillId);
    CandidateSkillDto saveCandidateSkill(CandidateSkillDto candidateSkillDto);
}
