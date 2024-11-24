package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;

import java.util.List;

public interface SkillModel {
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
}
