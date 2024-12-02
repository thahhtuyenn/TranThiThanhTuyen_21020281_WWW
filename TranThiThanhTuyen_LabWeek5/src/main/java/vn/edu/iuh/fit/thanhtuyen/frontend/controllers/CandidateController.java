package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.configs.UserDetails;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.SkillModel;

import java.security.Principal;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private JobModel jobModel;
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private SkillModel skillModel;

    @RequestMapping({"", "/", "/home"})
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String homeCandidate(Model model, HttpSession session, Principal principal,
                                @RequestParam(value = "action", required = false) String action,
                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "15") Integer size,
                                @RequestParam(value = "jobNameSearch", required = false) String jobName){
        Authentication authentication = (Authentication) principal;
        log.info("Authentication: " + authentication.getName());
        if (!authentication.isAuthenticated()){
            return "redirect:/login";
        }
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
        //Get info of candidate login
        Long candidateId = ((UserDetails) authentication.getPrincipal()).getUser().getId();
        CandidateDto candidateLogin = candidateModel.getCandidateById(candidateId);
        session.setAttribute("candidateLogin", candidateLogin);

        //Get jobs recommended for candidate
        PageDTO<JobDto> jobsRecommended = jobModel.getJobsSuitableForCandidate(candidateLogin.getId(), 50, 0, 30);
        //Get all jobs
        PageDTO<JobDto> jobs = jobModel.getAllJobsAsPaging(page, size);

        if (action.equalsIgnoreCase("searchJob") && !jobName.isEmpty()){
            page = 0;
            jobs = jobModel.getJobsByName(jobName, page, size);
        }
        model.addAttribute("candidateLogin", candidateLogin);
        model.addAttribute("jobs", jobs);
        model.addAttribute("action", action);
        model.addAttribute("jobRecommended", jobsRecommended);
        model.addAttribute("jobNameSearch", jobName);
        return "candidates/home";
    }

    @GetMapping("/show-job-detail")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String showJobDetail(Model model, HttpSession session, @RequestParam("jobId") Long jobId){
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        if (candidateLogin == null){
            return "redirect:/login";
        }
        JobDto job = jobModel.getJobById(jobId);

        //Get match skills between job and candidate
        List<JobSkillDto> jobSkills = job.getJobSkills();
        List<CandidateSkillDto> candidateSkills = candidateLogin.getCandidateSkills();
        List<Map<String, Object>> matchSkills = new ArrayList<>();
        for (JobSkillDto jobSkill : jobSkills){
            for (CandidateSkillDto candidateSkill : candidateSkills){
                Map<String, Object> matchSkill = new HashMap<>();
                if (jobSkill.getSkill().getId() == candidateSkill.getSkill().getId()){
                    float matchPercent = (float) candidateSkill.getSkillLevel().getValue() / jobSkill.getSkillLevel().getValue() * 100;
                    matchPercent = Math.round(matchPercent * 100.0) / 100.0F;
                    if (matchPercent > 100.0){
                        matchPercent = 100.0F;
                    }
                    matchSkill.put("skillName", jobSkill.getSkill().getSkillName());
                    matchSkill.put("matchPercent", matchPercent);
                    matchSkills.add(matchSkill);
                }
            }
        }

        //Get missing skills between job and candidate
        List<String> missingSkills = new ArrayList<>();
        for (JobSkillDto jobSkill : jobSkills){
            boolean isMatch = false;
            for (CandidateSkillDto candidateSkill : candidateSkills){
                if (jobSkill.getSkill().getId() == candidateSkill.getSkill().getId()){
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch){
                missingSkills.add(jobSkill.getSkill().getSkillName());
            }
        }

        model.addAttribute("missingSkills", missingSkills);
        model.addAttribute("matchSkills", matchSkills);
        model.addAttribute("job", job);
        model.addAttribute("candidateLogin", candidateLogin);
        return "candidates/show-job-detail";
    }

    @GetMapping("/edit-info")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String editInfo(Model model, HttpSession session){
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        if (candidateLogin == null){
            return "redirect:/login";
        }
        List<CandidateSkillDto> candidateSkills = candidateLogin.getCandidateSkills();
        List<SkillDto> skills = skillModel.getAllSkills();
        SkillLevel[] skillLevels = SkillLevel.values();

        model.addAttribute("skills", skills);
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("candidateSkills", candidateSkills);
        model.addAttribute("candidateLogin", candidateLogin);
        return "candidates/edit-info";
    }

    @GetMapping("/edit-skill")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String editSkills(HttpSession session, Model model, Principal principal,
                             @RequestParam(value = "skillId", required = false) Long skillId,
                             @RequestParam(value = "level", required = false) Integer level,
                             @RequestParam(value = "action", required = false) String action,
                             @RequestParam(value = "skill", required = false) Long skill,
                             @RequestParam(value = "moreInfo", required = false) String moreInfo){
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        if (candidateLogin == null){
            return "redirect:/login";
        }
        if (action != null){
            if(action.equalsIgnoreCase("addSkill")){
                SkillDto skillDto = skillModel.getSkillById(skill);
                SkillLevel skillLevel = SkillLevel.values()[level - 1];
                CandidateSkillDto candidateSkillDto = CandidateSkillDto.builder()
                        .skillId(skillDto.getId())
                        .canId(candidateLogin.getId())
                        .skillLevel(skillLevel)
                        .moreInfos(moreInfo)
                        .skill(skillDto)
                        .build();
                candidateSkillDto = candidateModel.saveCandidateSkill(candidateSkillDto);
                if(candidateSkillDto != null){
                    candidateLogin = candidateModel.getCandidateById(candidateLogin.getId());
                }
            } else if (action.equalsIgnoreCase("deleteSkill")) {
                boolean resultRemoveSkill = candidateModel.removeCandidateSkill(candidateLogin.getId(), skillId);
                if(resultRemoveSkill){
                    candidateLogin = candidateModel.getCandidateById(candidateLogin.getId());
                }
            }
        }
        session.setAttribute("candidateLogin", candidateLogin);
        return "redirect:/candidates/edit-info";
    }

    @PostMapping("/save-info")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public String saveInfo(HttpSession session, Principal principal,
                           @ModelAttribute("candidate") CandidateDto candidate){
        CandidateDto candidateLogin = (CandidateDto) session.getAttribute("candidateLogin");
        if (candidateLogin == null){
            return "redirect:/login";
        }
        candidate.setId(candidateLogin.getId());
        candidate.setCandidateSkills(candidateLogin.getCandidateSkills());
        candidate = candidateModel.saveCandidate(candidate);
        candidate = candidateModel.getCandidateById(candidate.getId());
        if (candidate != null){
            session.setAttribute("candidateLogin", candidate);
        }
        return "redirect:/candidates/edit-info";
    }
}
