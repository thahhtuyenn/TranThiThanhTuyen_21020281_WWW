package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CandidateService;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/api/candidates")
public class CandidateResource {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<CandidateDto> getCandidateByEmail(@RequestParam("email") String email) throws Exception {
        try {
            CandidateDto candidateDto = candidateService.findByEmail(email);
            return ResponseEntity.ok(candidateDto);
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

}
