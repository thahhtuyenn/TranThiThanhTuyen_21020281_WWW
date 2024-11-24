package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;

import java.util.List;

public interface SkillService {
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
}
