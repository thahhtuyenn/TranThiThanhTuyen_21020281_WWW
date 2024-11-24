package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.SkillService;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillResource {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() throws Exception {
        try {
            List<SkillDto> skills = skillService.getAllSkills();
            return ResponseEntity.ok(skills);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable("id") Long id) throws Exception {
        try {
            SkillDto skill = skillService.getSkillById(id);
            return ResponseEntity.ok(skill);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }
}
