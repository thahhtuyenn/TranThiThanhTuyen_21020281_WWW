package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {
    List<CandidateDto> getAll();
    CandidateDto getById(Long id);
    CandidateDto save(CandidateDto candidateDto);

    List<CandidateDto> getCandidatePaging(int page, int size);
    CandidateDto findByEmailAndPhone(String email, String phone);
}
