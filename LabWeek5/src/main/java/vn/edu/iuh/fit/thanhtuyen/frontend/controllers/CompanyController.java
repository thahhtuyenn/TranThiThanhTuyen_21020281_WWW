package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyModel companyModel;

    @Autowired
    private JobModel jobModel;


    @GetMapping("")
    public String getDashboard(HttpSession session, Model model, @RequestParam(name = "action", required = false) String action,
                                @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
                                @RequestParam(name = "jobName", required = false) String jobName) {
        if(page == null) {
            page = 0;
        }
        if(size == null) {
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
        if (action.equalsIgnoreCase("getJobsByCompanyId")){
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
                                  @RequestParam(name = "action", required = false) String action) {
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
        PageDTO<JobDto> jobs = jobModel.getJobsByCompanyId(companyLogin.getId(), page, size);
        model.addAttribute("jobs", jobs);
        model.addAttribute("action", action);
        model.addAttribute("companyLogin", companyLogin);

        return "companies/company-jobs";
    }
}
