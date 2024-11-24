package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private JobModel jobModel;

    @RequestMapping({"", "/", "/home"})
    public String homeCandidate(Model model, HttpSession session,
                                @RequestParam(value = "action", required = false) String action,
                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "15") Integer size,
                                @RequestParam(value = "jobNameSearch", required = false) String jobName){
        if(page == null){
            page = 0;
        }
        if (size == null){
            size = 15;
        }
        if(jobName == null){
            jobName = "";
        }
        if(action == null || jobName.isEmpty()){
            action = "getAll";
        }
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        if (candidateLogin == null){
            return "redirect:/login";
        }

        PageDTO<JobDto> jobsRecommended = jobModel.getJobsSuitableForCandidate(candidateLogin.getId(), 50, 0, 30);
        PageDTO<JobDto> jobs = jobModel.getAllJobsAsPaging(page, size);

        if (action.equalsIgnoreCase("searchJob") && !jobName.isEmpty()){
            page = 0;
            jobs = jobModel.getJobsByName(jobName, page, size);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("action", action);
        model.addAttribute("jobRecommended", jobsRecommended);
        model.addAttribute("candidateLogin", candidateLogin);
        return "candidates/home";
    }

    @GetMapping("/show-job-detail")
    public String showJobDetail(Model model, HttpSession session, @RequestParam("jobId") Long jobId){
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        if (candidateLogin == null){
            return "redirect:/login";
        }
        JobDto job = jobModel.getJobById(jobId);
        model.addAttribute("job", job);
        model.addAttribute("candidateLogin", candidateLogin);
        return "candidates/show-job-detail";
    }
}
