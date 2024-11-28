package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import com.neovisionaries.i18n.CountryCode;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.CandidateMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.CandidateSkillMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;
import vn.edu.iuh.fit.thanhtuyen.backend.models.CandidateSkill;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CandidateSkillMapper candidateSkillMapper;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

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
        //Get list of candidate skill
        List<CandidateSkillDto> candidateSkillDto = candidateDto.getCandidateSkills();
        //get candidate current to get username and password
        Candidate candidateOld = candidateRepository.findById(candidateDto.getId()).orElse(new Candidate());
        //Map candidateDto to candidate
        Candidate candidate = candidateMapper.toEntity(candidateDto);
        //if candidate is exist, update candidate
        if (candidateOld.getId() != null) {
            candidate = candidateMapper.partialUpdate(candidateDto, candidateOld);
        }
        //set list of candidate skill is empty
        candidate.setCandidateSkills(new ArrayList<>());
        candidate = candidateRepository.saveAndFlush(candidate);

        //save candidate skill
        for (CandidateSkillDto skillDto : candidateSkillDto) {
            CandidateSkill skill = candidateSkillMapper.toEntity(skillDto);
            skill.setCandidate(candidate);
            skill = candidateSkillRepository.saveAndFlush(skill);
        }
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

    @Override
    @Transactional
    public boolean removeCandidateSkill(Long candidateId, Long skillId) {
        Optional<CandidateSkill> candidateSkill = candidateSkillRepository.findByCandidateIdAndSkillId(candidateId, skillId);
        if (candidateSkill.isPresent()) {
            candidateSkillRepository.removeByCandidateIdAndSkillId(candidateId, skillId);
            return true;
        }
        return false;
    }

    @Override
    public CandidateSkillDto addCandidateSkill(CandidateSkillDto candidateSkillDto) {
        Candidate candidate =  candidateRepository.findById(candidateSkillDto.getCanId()).orElse(new Candidate());
        CandidateSkill candidateSkill = candidateSkillMapper.toEntity(candidateSkillDto);
        candidateSkill.setCandidate(candidate);
        candidateSkill = candidateSkillRepository.saveAndFlush(candidateSkill);
        return candidateSkillMapper.toDto(candidateSkill);
    }
}
