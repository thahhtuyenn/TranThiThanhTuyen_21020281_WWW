package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.SkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.SkillService;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillResource {
    @Autowired
    private SkillService skillService;

    @GET
    @RequestMapping("")
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
