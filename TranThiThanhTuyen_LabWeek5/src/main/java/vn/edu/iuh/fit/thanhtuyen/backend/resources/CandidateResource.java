package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CandidateService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/api/candidates")
public class CandidateResource {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto candidateDto) throws Exception {
        try {
            CandidateDto candidate = candidateService.save(candidateDto);
            return ResponseEntity.ok(candidate);
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(candidateService.getById(id));
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/getCandidatesSuitableForJob")
    public ResponseEntity<PageDTO<CandidateDto>> findCandidateMatchingJob(@RequestParam Long jobId, @RequestParam int per, @RequestParam int page, @RequestParam int size) throws Exception {
        try {
            PageDTO<CandidateDto> candidates = candidateService.findCandidateMatchingJob(jobId, per, page, size);
            return ResponseEntity.ok(candidates);
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/remove-candidate-skill")
    public ResponseEntity<Boolean> removeCandidateSkill(@RequestParam Long candidateId, @RequestParam Long skillId) throws Exception {
        try {
            return ResponseEntity.ok(candidateService.removeCandidateSkill(candidateId, skillId));
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @PostMapping("/update-candidate-skill")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateSkillDto> updateCandidateSkill(@RequestBody CandidateSkillDto candidateSkill) throws Exception {
        try {
            CandidateSkillDto candidateSkillDto = candidateService.addCandidateSkill(candidateSkill);
            return ResponseEntity.ok(candidateSkillDto);
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

}
