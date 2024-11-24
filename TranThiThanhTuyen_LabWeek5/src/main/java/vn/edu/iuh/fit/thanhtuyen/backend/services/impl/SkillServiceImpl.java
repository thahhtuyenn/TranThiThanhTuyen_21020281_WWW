package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.SkillMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Skill;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.SkillService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public List<SkillDto> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        return skill.map(skillMapper::toDto).orElse(null);
    }
}
