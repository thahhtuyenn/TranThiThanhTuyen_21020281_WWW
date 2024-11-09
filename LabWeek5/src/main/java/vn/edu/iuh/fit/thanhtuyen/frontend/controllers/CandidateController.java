package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateModel candidateModel;

    @Autowired
    private JobModel jobModel;

    @RequestMapping({"", "/"})
    public String getAllCandidates(HttpSession session, Model model, @RequestParam(value = "page", required = false,defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", required = false,defaultValue = "20") Integer size,
                                   @RequestParam(value = "action", required = false) String action,
                                   @RequestParam(value = "jobName", required = false) String jobName) {
        CandidateDto candidate = (CandidateDto) session.getAttribute("candidateLogin");
        PageDTO<JobDto> jobsMatchingWithCandidate = jobModel.getJobsMatchingWithCandidate(candidate.getId(), 50, page, size);
        List<String> colors = List.of("primary", "secondary", "success", "warning", "info", "light", "dark");
        String color = colors.get((int) (Math.random() * colors.size()));
        model.addAttribute("color", color);
        if (page == null){
            page = 0;
        }
        if (size == null){
            size = 20;
        }
        if (action == null){
            action = "jobsMatchingCandidate";
        }
        if (jobName == null){
            jobName = "";
        }
        if (action.equalsIgnoreCase("jobsMatchingCandidate")){
            model.addAttribute("action", action);
            model.addAttribute("jobs", jobsMatchingWithCandidate);
            model.addAttribute("candidateLogin", candidate);
            return "candidates/candidates";
        } else if (action.equalsIgnoreCase("showAll")) {
            PageDTO<JobDto> jobs = jobModel.getJobsPaging(page, size);
            model.addAttribute("action", action);
            model.addAttribute("jobs", jobs);
            model.addAttribute("candidateLogin", candidate);
            return "candidates/candidates";
        } else if (action.equalsIgnoreCase("search")) {
            PageDTO<JobDto> jobs = jobModel.getJobsByJobName(jobName, page, size);
            model.addAttribute("action", action);
            model.addAttribute("jobs", jobs);
            model.addAttribute("candidateLogin", candidate);
            return "candidates/candidates";
        }

        return "candidates/candidates";
    }
}
