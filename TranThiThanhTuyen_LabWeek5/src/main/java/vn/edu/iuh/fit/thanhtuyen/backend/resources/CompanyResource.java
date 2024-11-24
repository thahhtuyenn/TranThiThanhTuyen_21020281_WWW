package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyResource {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<CompanyDto> getCompanyByEmail(@RequestParam("email") String email) throws Exception {
        try {
            return ResponseEntity.ok(companyService.findByEmail(email));
        }catch (Exception e) {
            throw new Exception("Error: "  + e.getMessage(), e);
        }
    }


}
