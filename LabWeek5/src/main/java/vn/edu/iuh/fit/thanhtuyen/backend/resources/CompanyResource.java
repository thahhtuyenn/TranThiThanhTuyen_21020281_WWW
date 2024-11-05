package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() throws Exception {
        try {
            return ResponseEntity.ok(companyService.getAllCompany());
        }catch (Exception e) {
            throw new Exception("Error: "  + e.getMessage(), e);
        }
    }

    @GetMapping("/companyLogin")
    public ResponseEntity<CompanyDto> findByEmailAndPhone(@RequestParam String email, @RequestParam String phone) throws Exception {
        try {
            return ResponseEntity.ok(companyService.findByEmailAndPhone(email, phone));
        }catch (Exception e){
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }
}
