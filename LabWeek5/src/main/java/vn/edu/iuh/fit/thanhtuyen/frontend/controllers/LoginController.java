package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private CandidateModel candidateModel;

    @Autowired
    private JobModel jobModel;

    @Autowired
    private CompanyModel companyModel;

    @GetMapping("")
    public String login(){
        return "login";
    }

    @PostMapping("")
    public String login(HttpSession session, Model model, @RequestParam(value = "email", required = false) String email,
                        @RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "type", required = false) String type,
                        @RequestParam("action") String action){
        if(action.equals("login")){
            if (email == null || phone == null) {
                return "login";
            }
            if (type.equals("candidate")) {
                CandidateDto candidateDto = candidateModel.getCandidateByEmailAndPhone(email, phone);
                if (candidateDto != null) {
                    session.setAttribute("candidateLogin", candidateDto);
                    return "redirect:/candidates";
                }
            }else if (type.equals("company")){
                CompanyDto companyDto = companyModel.getCompanyByEmailAndPhone(email, phone);
                if (companyDto != null) {
                    session.setAttribute("companyLogin", companyDto);
                    return "redirect:/companies";
                }
            } else if (type.equals("admin")) {
                return "redirect:/admin";
            }
        } else if(action.equals("logout")){
            session.removeAttribute("candidateLogin");
            session.removeAttribute("companyLogin");
            session.removeAttribute("adminLogin");
            session.removeAttribute("jobSkills");
            return "redirect:/home";
        }
        return "login";
    }
}
