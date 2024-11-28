package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;

import java.awt.print.Printable;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private CompanyModel companyModel;

    @GetMapping("")
    public String login(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/check-role")
    public String checkRole(Principal principal){
        Authentication authentication = (Authentication) principal;
        if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("CANDIDATE"))) {
            return "redirect:/candidates"; // URL dành cho candidate
        } else if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("COMPANY"))) {
            return "redirect:/companies"; // URL dành cho company
        } else if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            return "redirect:/admin"; // URL dành cho admin
        }
        return "redirect:/";
    }
}
