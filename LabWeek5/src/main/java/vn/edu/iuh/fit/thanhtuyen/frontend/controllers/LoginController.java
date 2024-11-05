package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.impl.CandidateModelImpl;

@Controller
@RequestMapping("login")
public class LoginController {

    private CandidateModel candidateModel = new CandidateModelImpl();

    @PostMapping("")
    public String login(Model model, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "type", required = false) String type, @RequestParam("action") String action){
        if(action.equals("login")){
            if (email == null || phone == null) {
                return "index";
            }
            if (type.equals("candidate")) {
                CandidateDto candidateDto = candidateModel.getCandidateByEmailAndPhone(email, phone);
                if (candidateDto != null) {
                    model.addAttribute("candidate", candidateDto);
                    return "candidates/candidates";
                }
//        } else {
//            //CompanyDto companyDto = companyModel.getCompanyByEmailAndPhone(email, password);
//            //if (companyDto != null) {
//            //    model.addAttribute("company", companyDto);
//            //    return "company";
//            //}
//        }
            }else if (type.equals("company")){
                return "redirect:/companies";
            } else if (type.equals("admin")) {
                return "redirect:/admin";
            }
        } else if(action.equals("logout")){
            return "index";
        }
        return "index";
    }
}
