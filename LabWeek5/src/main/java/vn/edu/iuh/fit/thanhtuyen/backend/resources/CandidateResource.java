package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CandidateService;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateResource {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates() throws Exception {
        try {
            return ResponseEntity.ok(candidateService.getAll());
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<CandidateDto> getCandidateById(@RequestParam Long id) throws Exception {
        try {
            return ResponseEntity.ok(candidateService.getById(id));
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/paging")
    public ResponseEntity<List<CandidateDto>> getCandidatePaging(@RequestParam int page, @RequestParam int size) throws Exception {
        try {
            return ResponseEntity.ok(candidateService.getCandidatePaging(page, size));
        }catch (Exception e){
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/candidateLogin")
    public ResponseEntity<CandidateDto> findByEmailAndPhone(@RequestParam String email, @RequestParam String phone) throws Exception {
        try {
            CandidateDto candidateDto = candidateService.findByEmailAndPhone(email, phone);
            return ResponseEntity.ok(candidateDto);
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/candidateMatchingJob")
    public ResponseEntity<PageDTO<CandidateDto>> findCandidateMatchingJob(@RequestParam Long jobId, @RequestParam int per, @RequestParam int page, @RequestParam int size) throws Exception {
        try {
            PageDTO<CandidateDto> candidates = candidateService.findCandidateMatchingJob(jobId, per, page, size);
            return ResponseEntity.ok(candidates);
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }
}
