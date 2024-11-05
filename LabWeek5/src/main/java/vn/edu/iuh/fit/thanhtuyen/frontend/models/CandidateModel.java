package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;

import java.util.List;

public interface CandidateModel {
    List<CandidateDto> getAllCandidate();
    CandidateDto getCandidateById(Long id);
    boolean insertCandidate(CandidateDto candidateDto);
    List<CandidateDto> getCandidatePage(int page, int size);
    CandidateDto getCandidateByEmailAndPhone(String email, String phone);
}
