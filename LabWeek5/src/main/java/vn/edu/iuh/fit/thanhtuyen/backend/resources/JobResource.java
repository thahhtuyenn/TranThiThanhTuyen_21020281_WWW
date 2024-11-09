package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.services.JobService;

import javax.ws.rs.GET;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobResource {
    @Autowired
    private JobService jobService;

    @GET
    @RequestMapping("")
    public ResponseEntity<List<JobDto>> getAllJobs() throws Exception {
        try {
            List<JobDto> jobs = jobService.getAllJobs();
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GET
    @RequestMapping("/paging")
    public ResponseEntity<PageDTO<JobDto>> getJobsPaging(@RequestParam int page,@RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsPaging(page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }

    }

    @GET
    @RequestMapping("/search")
    public ResponseEntity<PageDTO<JobDto>> getJobsByCityAndJobName(@RequestParam String jobName, @RequestParam int page, int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsByName(jobName, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }

    }

    @GET
    @RequestMapping("/id")
    public ResponseEntity<JobDto> getJobById(@RequestParam Long id) throws Exception {
        try {
            JobDto job = jobService.getJobById(id);
            return ResponseEntity.ok(job);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }

    }

    @GET
    @RequestMapping("/countPage")
    public ResponseEntity<Integer> countPageJobs(@RequestParam int size) throws Exception {
        try {
            int count = jobService.countPageJobs(size);
            return ResponseEntity.ok(count);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }

    }

    @GET
    @RequestMapping("/jobsMatchingCandidate")
    public ResponseEntity<PageDTO<JobDto>> getJobsMatchingCandidate(@RequestParam Long candidateId,@RequestParam int per,@RequestParam int page,@RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsMatchingCandidate(candidateId, per, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GET
    @RequestMapping("/jobsOfCompany")
    public ResponseEntity<PageDTO<JobDto>> getJobsOfCompany(@RequestParam Long companyId,@RequestParam int page,@RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsByCompanyId(companyId, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }

    @GET
    @RequestMapping("/searchForCompany")
    public ResponseEntity<PageDTO<JobDto>> getJobsByCompanyId(@RequestParam Long companyId, @RequestParam String jobName, @RequestParam int page, @RequestParam int size) throws Exception {
        try {
            PageDTO<JobDto> jobs = jobService.getJobsByCompanyAndName(companyId, jobName, page, size);
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }
}
