package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.services.JobService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/api/jobs")
public class JobResource {
    @Autowired
    private JobService jobService;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDto> saveJob(@RequestBody JobDto jobDto) throws Exception {
        try {
            JobDto job = jobService.saveJob(jobDto);
            return ResponseEntity.ok(job);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<PageDTO<JobDto>> getJobsPaging(@RequestParam int page, @RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsPaging(page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) throws Exception {
        try {
            JobDto job = jobService.getJobById(id);
            return ResponseEntity.ok(job);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/searchByJobName")
    public ResponseEntity<PageDTO<JobDto>> getJobsByCityAndJobName(@RequestParam String jobName, @RequestParam int page, @RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsByName(jobName, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/jobsSuitableForCandidate")
    public ResponseEntity<PageDTO<JobDto>> getJobsMatchingCandidate(@RequestParam Long candidateId,@RequestParam int per,@RequestParam int page,@RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsMatchingCandidate(candidateId, per, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/getJobsByCompany")
    public ResponseEntity<PageDTO<JobDto>> getJobsOfCompany(@RequestParam Long companyId,@RequestParam int page,@RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsByCompanyId(companyId, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/searchJobsOfCompany")
    public ResponseEntity<PageDTO<JobDto>> getJobsByCompanyId(@RequestParam Long companyId, @RequestParam String jobName, @RequestParam int page, @RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsByCompanyAndName(companyId, jobName, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

}
