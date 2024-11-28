package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.configs.UserDetails;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

import java.security.Principal;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private JobModel jobModel;

    @GetMapping({"", "/", "/home"})
    @PreAuthorize("hasAuthority('COMPANY')")
    public String homeCompany(HttpSession session, Model model, Principal principal,
                              @RequestParam(value = "action", required = false) String action,
                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "15") Integer size,
                              @RequestParam(value = "jobNameSearch", required = false) String jobName){
        Authentication authentication = (Authentication) principal;
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
            action = "getAllJob";
        }
        //Get info of company login
        Long companyId = ((UserDetails) authentication.getPrincipal()).getUser().getId();


        return "companies/home";
    }
}
