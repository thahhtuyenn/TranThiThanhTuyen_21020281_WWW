package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserRegisterDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CompanyModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.UserModel;

@Slf4j
@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private JobModel jobModel;

    @Autowired
    private UserModel userModel;

    @Autowired
    private CompanyModel companyModel;

    @Autowired
    private CandidateModel candidateModel;

    @GetMapping({"/", "/dashboard"})
    public String getDashboard(Model model, @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size,
                               @RequestParam(value = "action", required = false) String action,
                               @RequestParam(value = "jobNameSearch", required = false) String jobNameSearch) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 15;
        }
        if (jobNameSearch == null) {
            jobNameSearch = "";
        }
        PageDTO<JobDto> jobs = jobModel.getAllJobsAsPaging(page, size);
        if (action != null && action.equals("searchJob") && !jobNameSearch.isEmpty()) {
            page = 0;
            jobs = jobModel.getJobsByName(jobNameSearch, page, size);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("jobNameSearch", jobNameSearch);
        return "index";
    }

    @GetMapping("/job-detail")
    public String getJobDetailDashboard(Model model, @RequestParam(value = "id", required = false) Long id) {
        JobDto job = jobModel.getJobById(id);
        model.addAttribute("job", job);
        return "jobs/job-detail";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session, @ModelAttribute("userRegister")UserRegisterDto userRegisterDto) {
        UserRegisterDto userRegister = (UserRegisterDto) session.getAttribute("userRegister");
        if (userRegister == null) {
            userRegister = new UserRegisterDto();
        }
        String messageUsername = session.getAttribute("messageUsername") != null ? session.getAttribute("messageUsername").toString() : "";
        String messagePassword = session.getAttribute("messagePassword") != null ? session.getAttribute("messagePassword").toString() : "";
        model.addAttribute("userRegister", userRegister);
        model.addAttribute("messageUsername", messageUsername);
        model.addAttribute("messagePassword", messagePassword);
        return "register";
    }

    @PostMapping("/do-register")
    public String doRegister(HttpSession session,
                             @ModelAttribute("userRegister") UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equalsIgnoreCase(userRegisterDto.getConfirmPassword())){
            session.setAttribute("messagePassword", "Mật khẩu và xác nhận mật khẩu không trùng khớp");
            session.setAttribute("userRegister", userRegisterDto);
            return "redirect:/register";
        }
        UserDto user = userModel.loadByUsername(userRegisterDto.getUsername());
        if (user != null) {
            session.setAttribute("messageUsername", "Tên đăng nhập đã tồn tại");
            session.setAttribute("userRegister", userRegisterDto);
            return "redirect:/register";
        }

        boolean result = userModel.register(userRegisterDto);
        if (result) {
            session.removeAttribute("messageUsername");
            session.removeAttribute("messagePassword");
            session.removeAttribute("userRegister");
            return "redirect:/login";
        }
        return "redirect:/register";
    }

}
