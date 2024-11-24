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
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private CompanyModel companyModel;

    @GetMapping("")
    public String login(){
        return "login";
    }

    @PostMapping("")
    public String doLogin(Model model, HttpSession session,
                          @RequestParam(value = "email", required = false) String email,
                          @RequestParam(value = "role", required = false) String role,
                          @RequestParam(value = "action", required = false) String action){
        if(action.equals("login")){
            if(role.equals("candidate")){
                CandidateDto candidateLogin = candidateModel.getCandidateByEmail(email);
                if(candidateLogin != null){
                    session.setAttribute("candidateLogin", candidateLogin);
                    return "redirect:/candidates";
                }
            }
        } else if (action.equals("logout")) {
            session.removeAttribute("candidateLogin");
            session.removeAttribute("companyLogin");
            session.removeAttribute("adminLogin");
            session.removeAttribute("jobSkills");
            return "redirect:/dashboard";
        }
        return "login";
    }
}
