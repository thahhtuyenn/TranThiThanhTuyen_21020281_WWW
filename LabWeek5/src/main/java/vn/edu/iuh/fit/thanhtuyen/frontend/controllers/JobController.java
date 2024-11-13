package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.SkillModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobModel jobModel;

    @Autowired
    private CandidateModel candidateModel;

    @Autowired
    private SkillModel skillModel;

    @RequestMapping("/job-detail-candidate")
    public String getJobDetailCandidate(Model model, HttpSession session, @RequestParam(value = "id", required = false) Long jobId,
                                        @RequestParam(value = "action", required = false) String action) {
        if (action == null) {
            action = "";
        }
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        JobDto job = jobModel.getJobById(jobId);
        if (action.equalsIgnoreCase("logout")) {
            session.removeAttribute("candidateLogin");
            return "redirect:/";
        }
        model.addAttribute("job", job);
        model.addAttribute("candidateLogin", candidateLogin);
        return "jobs/job-detail-candidate";
    }

    @RequestMapping("/job-detail")
    public String getJobDetailHome(Model model, HttpSession session, @RequestParam(value = "id", required = false) Long jobId) {
        JobDto job = jobModel.getJobById(jobId);
        model.addAttribute("job", job);
        return "jobs/job-detail";
    }

    @RequestMapping("/job-detail-company")
    public String getJobDetailCompany(Model model, HttpSession session, @RequestParam(value = "id", required = false) Long jobId,
                                      @RequestParam(value = "per", required = false) Integer per,
                                      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                      @RequestParam(value = "action", required = false) String action) {
        if (per == null) {
            per = 50;
        }
        if (action == null) {
            action = "filter";
        }
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        if (action.equalsIgnoreCase("filter")) {
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


    @PostMapping("/save-job")
    public String createJob(Model model, HttpSession session,
                            @RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "jobName", required = false) String jobName,
                            @RequestParam(value = "jobDescription", required = false) String jobDescription) {
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        List<JobSkillDto> jobSkills = (List<JobSkillDto>) session.getAttribute("jobSkills");
        if (jobName == null || jobDescription == null) {
            return "redirect:/jobs/edit-job";
        }
        if (companyLogin == null) {
            return "redirect:/login";
        }
        if (jobSkills == null || jobSkills.isEmpty()) {
            return "redirect:/jobs/edit-job";
        }
        if (id == null) {
            id = 0L;
        }
        JobDto job = JobDto.builder()
                .id(id)
                .jobName(jobName)
                .jobDesc(jobDescription)
                .jobSkills(jobSkills)
                .company(companyLogin)
                .build();
        jobModel.saveJob(job);
        session.removeAttribute("jobSkills");
        model.addAttribute("companyLogin", companyLogin);
        return "redirect:/companies";
    }

    @GetMapping("/edit-job")
    public String getEditJob(Model model, HttpSession session, @RequestParam(name = "id", required = false) Long id,
                             @RequestParam(name = "action", required = false) String action) {
        if (action == null) {
            action = "addJob";
        }
        if (id == null) {
            id = 0L;
        }
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        if (companyLogin == null) {
            return "redirect:/login";
        }
        List<JobSkillDto> jobSkills = (List<JobSkillDto>) session.getAttribute("jobSkills");
        SkillLevel[] skillLevels = SkillLevel.values();
        List<SkillDto> skills = skillModel.getAllSkills();
        JobDto job = jobModel.getJobById(id);
        if (jobSkills == null || jobSkills.isEmpty()) {
            jobSkills = new ArrayList<>();
        }
        if (action.equalsIgnoreCase("addJob")) {
            jobSkills = jobSkills.isEmpty() ? new ArrayList<>() : jobSkills;
        } else if (action.equalsIgnoreCase("editJob")) {
            if (job != null) {
                if (!jobSkills.isEmpty()) {
                    JobSkillDto jobSkill= jobSkills.get(0);
                    jobSkills = Objects.equals(job.getId(), jobSkill.getJobId()) ? jobSkills : job.getJobSkills();
                }else {
                    jobSkills = job.getJobSkills();
                }
            } else {
                model.addAttribute("companyLogin", companyLogin);
                return "redirect:/companies";
            }
        }
        session.setAttribute("jobSkills", jobSkills);
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("skills", skills);
        model.addAttribute("job", job);
        model.addAttribute("action", action);
        model.addAttribute("jobSkills", jobSkills);
        model.addAttribute("companyLogin", companyLogin);
        return "companies/edit-job";
    }

    @GetMapping("/choose-skill")
    public String getChooseSkill(Model model, HttpSession session, @RequestParam(name = "selectSkill", required = false) Long id,
                                 @RequestParam(name = "action", required = false) String action,
                                 @RequestParam(name = "skillLevel", required = false) Integer skillLevel,
                                 @RequestParam(name = "moreInfo", required = false) String moreInfo,
                                 @RequestParam(name = "skillId", required = false) Long skillId,
                                 @RequestParam(name = "jobId", required = false) Long jobId) {
        if (action == null) {
            action = "";
        }
        if(jobId == null) {
            jobId = 0L;
        }
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");
        JobDto job = jobModel.getJobById(jobId);
        List<JobSkillDto> jobSkills = (List<JobSkillDto>) session.getAttribute("jobSkills");
        if (companyLogin == null) {
            return "redirect:/login";
        }
        if(jobSkills == null || jobSkills.isEmpty()) {
            jobSkills = new ArrayList<>();
        }
        model.addAttribute("companyLogin", companyLogin);
        if (action.equalsIgnoreCase("addSkill")) {
            SkillDto skill = skillModel.getSkillById(id);
            if (skill != null) {
                JobSkillDto jobSkill = JobSkillDto.builder()
                        .jobId(null).skillId(id).skill(skill)
                        .skillLevel(SkillLevel.values()[skillLevel - 1])
                        .moreInfos(moreInfo).build();

                if (job != null) {
                    jobSkill.setJobId(job.getId());
                    jobSkills.add(jobSkill);
                    session.setAttribute("jobSkills", jobSkills);
                    model.addAttribute("jobSkills", jobSkills);
                    return "redirect:/jobs/edit-job?action=editJob" + "&id=" + jobId;
                }else {
                    jobSkills.add(jobSkill);
                    session.setAttribute("jobSkills", jobSkills);
                    model.addAttribute("jobSkills", jobSkills);
                    return "redirect:/jobs/edit-job";
                }
            }
        } else if (action.equalsIgnoreCase("removeSkill")) {
            SkillDto skill = skillModel.getSkillById(skillId);
            JobSkillDto jobSkill = jobSkills.stream().filter(js -> js.getSkill().getId().equals(skillId)).toList().get(0);
            jobSkills.remove(jobSkill);

            session.setAttribute("jobSkills", jobSkills);
            if (job != null) {
                return "redirect:/jobs/edit-job?action=editJob" + "&id=" + jobId;
            }
            return "redirect:/jobs/edit-job";
        }else {
            session.setAttribute("jobSkills", jobSkills);
            if (job != null) {
                return "redirect:/jobs/edit-job?action=editJob" + "&id=" + jobId;
            }
            return "redirect:/jobs/edit-job";
        }
        return "redirect:/jobs/edit-job";
    }
}
