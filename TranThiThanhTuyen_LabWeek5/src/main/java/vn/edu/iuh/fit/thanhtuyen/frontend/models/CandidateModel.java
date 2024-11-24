package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import org.springframework.data.domain.Page;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

public interface CandidateModel {
    CandidateDto getCandidateByEmail(String email);
    PageDTO<CandidateDto> getCandidateSuitableForJob(Long jobId, int per, int page, int size);
}
