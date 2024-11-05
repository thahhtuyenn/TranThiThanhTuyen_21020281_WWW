package vn.edu.iuh.fit.thanhtuyen.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.CandidateModel;

import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateModel candidateModel;

    @RequestMapping("")
    public String getAllCandidates(Model model) {
        List<CandidateDto> candidateDtos = candidateModel.getAllCandidate();
        model.addAttribute("candidates", candidateDtos);

        return "candidates/candidates";
    }
}
