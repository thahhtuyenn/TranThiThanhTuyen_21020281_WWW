package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.*;
import vn.edu.iuh.fit.thanhtuyen.backend.enums.SkillLevel;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Skill;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.SkillModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyModel companyModel;

    @Autowired
    private JobModel jobModel;

    @Autowired
    private SkillModel skillModel;


    @GetMapping("")
    public String getDashboard(HttpSession session, Model model, @RequestParam(name = "action", required = false) String action,
                               @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                               @RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
                               @RequestParam(name = "jobName", required = false) String jobName) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 20;
        }
        if (action == null) {
            action = "getJobsByCompanyId";
        }
        if (jobName == null) {
            jobName = "";
        }

        CompanyDto companyDto = (CompanyDto) session.getAttribute("companyLogin");
        if (companyDto == null) {
            return "redirect:/login";
        }
        PageDTO<JobDto> jobs = jobModel.getJobsByCompanyId(companyDto.getId(), page, size);
        if (action.equalsIgnoreCase("getJobsByCompanyId")) {
            model.addAttribute("jobs", jobs);
            model.addAttribute("companyLogin", companyDto);
            model.addAttribute("action", action);
            return "companies/companies";
        } else if (action.equalsIgnoreCase("searchJobsByCompanyId")) {

            if (jobName.isEmpty()) {
                jobs = jobModel.getJobsByCompanyId(companyDto.getId(), page, size);
            } else {
                jobs = jobModel.getJobsByCompanyAndJobName(companyDto.getId(), jobName, page, size);
            }
            model.addAttribute("action", action);
            model.addAttribute("jobs", jobs);
            model.addAttribute("companyLogin", companyDto);
            return "companies/companies";
        }
        return "companies/companies";
    }

    @GetMapping("/manage-jobs")
    public String getJobOfCompany(Model model, HttpSession session, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                  @RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
                                  @RequestParam(name = "action", required = false) String action,
                                  @RequestParam(name = "jobName", required = false) String jobName) {
        CompanyDto companyLogin = (CompanyDto) session.getAttribute("companyLogin");

        if (companyLogin == null) {
            return "redirect:/login";
        }
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 20;
        }
        if (action == null) {
            action = "getJobsByCompanyId";
        }
        if (jobName == null) {
            jobName = "";
        }
        PageDTO<JobDto> jobs = jobModel.getJobsByCompanyId(companyLogin.getId(), page, size);
        if (action.equalsIgnoreCase("getJobsByCompanyId")) {

            model.addAttribute("jobs", jobs);
            model.addAttribute("companyLogin", companyLogin);
            model.addAttribute("action", action);
            return "companies/company-jobs";
        } else if (action.equalsIgnoreCase("searchJobsByCompanyId")) {
            jobs = jobModel.getJobsByCompanyAndJobName(companyLogin.getId(), jobName, page, size);
            model.addAttribute("jobs", jobs);
            model.addAttribute("companyLogin", companyLogin);
            model.addAttribute("action", action);
            return "companies/company-jobs";
        }
        else if(action.equalsIgnoreCase("editJob")){


            model.addAttribute("companyLogin", companyLogin);

            return "redirect:/jobs/edit-job";
        }

        return "companies/company-jobs";
    }


}
