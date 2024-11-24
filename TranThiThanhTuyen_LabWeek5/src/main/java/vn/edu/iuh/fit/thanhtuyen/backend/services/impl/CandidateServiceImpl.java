package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.CandidateMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CandidateService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public List<CandidateDto> getAll() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream().map(candidateMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CandidateDto getById(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        return candidate.map(candidateMapper::toDto).orElse(new CandidateDto());
    }

    @Override
    public CandidateDto save(CandidateDto candidateDto) {
        Candidate candidate = candidateMapper.toEntity(candidateDto);
        candidate = candidateRepository.save(candidate);
        return candidateMapper.toDto(candidate);
    }

    @Override
    public List<CandidateDto> getCandidatePaging(int page, int size) {
        Page<Candidate> candidates = candidateRepository.findAll(PageRequest.of(page, size));
        return candidates.stream().map(candidateMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CandidateDto findByEmail(String email) {
        Candidate candidate = candidateRepository.findByEmail(email);
        return candidateMapper.toDto(candidate);
    }

    @Override
    public PageDTO<CandidateDto> findCandidateMatchingJob(Long jobId, int per, int page, int size) {
        Page<Candidate> candidates = candidateRepository.findCandidateMatchingJob(jobId, per, PageRequest.of(page, size));
        PageDTO<CandidateDto> pageDTO = new PageDTO<>();
        List<CandidateDto> candidateDtos = candidates.stream().map(candidateMapper::toDto).collect(Collectors.toList());
        pageDTO.setContent(candidateDtos);
        pageDTO.setTotalPages(candidates.getTotalPages());
        pageDTO.setSize(candidates.getSize());
        pageDTO.setPage(candidates.getNumber());
        return pageDTO;
    }
}
