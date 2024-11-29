package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.configs.UserDetails;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;
import vn.edu.iuh.fit.thanhtuyen.backend.models.JobSkill;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.SkillModel;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private JobModel jobModel;
    @Autowired
    private CompanyModel companyModel;
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private SkillModel skillModel;

    @GetMapping({"", "/", "/home"})
    @PreAuthorize("hasAuthority('COMPANY')")
    public String homeCompany(HttpSession session, Model model, Principal principal,
                              @RequestParam(value = "action", required = false) String action,
                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "15") Integer size,
                              @RequestParam(value = "jobName", required = false) String jobName) {
        Authentication authentication = (Authentication) principal;
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 15;
        }
        if (jobName == null) {
            jobName = "";
        }
        if (action == null || jobName.isEmpty()) {
            action = "getAllJob";
        }
        //Get info of company login
        Long companyId = ((UserDetails) authentication.getPrincipal()).getUser().getId();
        CompanyDto companyLogin = companyModel.getCompanyById(companyId);
        session.setAttribute("companyLogin", companyLogin);

        PageDTO<JobDto> jobs = jobModel.getJobsByCompany(companyId, page, size);
        if (!jobName.isEmpty() && action.equals("searchJob")) {
            page = 0;
            jobs = jobModel.getJobsByCompanyAndJobName(companyId, jobName, page, size);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("companyLogin", companyLogin);
        return "companies/home";
    }

    @GetMapping("/show-job-detail")
    @PreAuthorize("hasAuthority('COMPANY')")
    public String showJobDetail(@RequestParam("jobId") Long jobId, Model model, Principal principal, HttpSession session,
                                @RequestParam(value = "per", required = false, defaultValue = "50") Integer per,
                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "15") Integer size,
                                @RequestParam(value = "action", required = false) String action) {
        Authentication authentication = (Authentication) principal;
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 15;
        }
        if (per == null) {
            per = 50;
        }
        if (action == null) {
            action = "default";
        }
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        JobDto job = jobModel.getJobById(jobId);
        PageDTO<CandidateDto> candidates = candidateModel.getCandidateSuitableForJob(jobId, per, page, size);
        if (action.equals("filterCandidate")) {
            page = 0;
            candidates = candidateModel.getCandidateSuitableForJob(jobId, per, page, size);
        }

        model.addAttribute("per", per);
        model.addAttribute("candidates", candidates);
        model.addAttribute("job", job);
        model.addAttribute("companyLogin", companyLogin);
        return "companies/show-job-detail";
    }

    @GetMapping("/show-info-candidate-for-job")
    @PreAuthorize("hasAuthority('COMPANY')")
    public String showInfoCandidateForJob(@RequestParam(value = "candidateId", required = false) Long candidateId, Model model, Principal principal,
                                          @RequestParam(value = "jobId", required = false) Long jobId, HttpSession session) {
        Authentication authentication = (Authentication) principal;
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        CandidateDto candidate = candidateModel.getCandidateById(candidateId);

        model.addAttribute("jobId", jobId);
        model.addAttribute("candidate", candidate);
        model.addAttribute("companyLogin", companyLogin);
        return "companies/show-info-candidate";
    }

    @GetMapping("/job-manager")
    @PreAuthorize("hasAuthority('COMPANY')")
    public String jobManager(Model model, Principal principal, HttpSession session,
                             @RequestParam(value = "action", required = false) String action,
                             @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                             @RequestParam(value = "jobName", required = false) String jobName) {
        Authentication authentication = (Authentication) principal;
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        if (jobName == null) {
            jobName = "";
        }
        if (action == null || jobName.isEmpty()) {
            action = "getAllJob";
        }
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        Long companyId = companyLogin.getId();
        PageDTO<JobDto> jobs = jobModel.getJobsByCompany(companyId, page, size);
        if (!jobName.isEmpty() && action.equals("searchJob")) {
            page = 0;
            jobs = jobModel.getJobsByCompanyAndJobName(companyId, jobName, page, size);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("companyLogin", companyLogin);
        return "companies/job-manager";
    }

    @GetMapping("edit-job")
    @PreAuthorize("hasAuthority('COMPANY')")
    public String editJob(Model model, Principal principal, HttpSession session,
                          @RequestParam(value = "jobId", required = false) Long jobId) {
        Authentication authentication = (Authentication) principal;
        String title = "";
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        List<SkillDto> skills = skillModel.getAllSkills();
        SkillLevel[] skillLevels = SkillLevel.values();
        List<JobSkillDto> jobSkills = new ArrayList<>();
        JobDto job = null;
        if (jobId != null) {
            job = jobModel.getJobById(jobId);
            title = "Cập nhật thông tin công việc";
            jobSkills = job.getJobSkills();
        } else {
            job = new JobDto();
            title = "Đăng tuyển công việc";
            jobSkills = (List<JobSkillDto>) session.getAttribute("jobSkills");
            if (jobSkills == null) {
                jobSkills = new ArrayList<>();
            }
        }

        model.addAttribute("job", job);
        model.addAttribute("title", title);
        model.addAttribute("skills", skills);
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("jobSkills", jobSkills);
        model.addAttribute("companyLogin", companyLogin);
        return "companies/edit-job";
    }
}
