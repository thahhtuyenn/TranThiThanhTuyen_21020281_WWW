package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.JobModel;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private JobModel jobModel;

    @GetMapping({"/", "/dashboard"})
    public String getDashboard(Model model, @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size,
                               @RequestParam(value = "action", required = false) String action,
                               @RequestParam(value = "jobNameSearch", required = false) String jobNameSearch){
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 15;
        }
        if(jobNameSearch == null){
            jobNameSearch = "";
        }
        PageDTO<JobDto> jobs = jobModel.getAllJobsAsPaging(page, size);
        if (action != null && action.equals("searchJob") && !jobNameSearch.isEmpty()) {
            page = 0;
            jobs = jobModel.getJobsByName(jobNameSearch, page, size);
        }
        model.addAttribute("jobs", jobs);
        return "index";
    }

    @GetMapping ("/job-detail")
    public String getJobDetailDashboard(Model model, @RequestParam(value = "id", required = false) Long id){
        JobDto job = jobModel.getJobById(id);
        model.addAttribute("job", job);
        return "jobs/job-detail";
    }

}
