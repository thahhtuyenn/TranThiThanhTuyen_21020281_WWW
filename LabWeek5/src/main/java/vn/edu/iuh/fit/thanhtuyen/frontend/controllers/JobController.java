package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobModel jobModel;

    @Autowired
    private CandidateModel candidateModel;

    @RequestMapping("/job-detail-candidate")
    public String getJobDetailCandidate(Model model, HttpSession session, @RequestParam(value = "id", required = false) Long jobId,
                                        @RequestParam(value = "action" , required = false) String action){
        if (action == null){
            action = "";
        }


        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        JobDto job = jobModel.getJobById(jobId);
        if (action.equalsIgnoreCase("logout")){
            session.removeAttribute("candidateLogin");
            return "redirect:/";
        }

        model.addAttribute("job", job);
        model.addAttribute("candidateLogin", candidateLogin);
        return "jobs/job-detail-candidate";
    }

    @RequestMapping("/job-detail")
    public String getJobDetailHome(Model model, HttpSession session, @RequestParam(value = "id", required = false) Long jobId){
        JobDto job = jobModel.getJobById(jobId);
        model.addAttribute("job", job);
        return "jobs/job-detail";
    }

    @RequestMapping("/job-detail-company")
    public String getJobDetailCompany(Model model, HttpSession session, @RequestParam(value = "id", required = false) Long jobId,
                                      @RequestParam(value = "per" , required = false) Integer per,
                                      @RequestParam(value = "page" , required = false, defaultValue = "0") Integer page,
                                      @RequestParam(value = "size" , required = false, defaultValue = "10") Integer size,
                                      @RequestParam(value = "action" , required = false) String action){
        if (per == null){
            per = 50;
        }
        if (action == null){
            action = "filter";
        }
        if (page == null){
            page = 0;
        }
        if (size == null){
            size = 10;
        }
        if (action.equalsIgnoreCase("filter")){
            CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
            JobDto job = jobModel.getJobById(jobId);
            PageDTO<CandidateDto> candidates = candidateModel.getCandidateMatchWithJob(jobId, per, page, size);
            model.addAttribute("per", per);
            model.addAttribute("candidates", candidates);
            model.addAttribute("companyLogin", companyLogin);
            model.addAttribute("job", job);
            model.addAttribute("action", action);
            return "jobs/job-detail-company";
        }
        return "jobs/job-detail-company";
    }
}
