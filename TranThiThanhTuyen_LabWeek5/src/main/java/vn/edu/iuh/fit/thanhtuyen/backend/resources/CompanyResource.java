package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyResource {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(companyService.getCompanyById(id));
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @PostMapping
    public  ResponseEntity<CompanyDto> saveCompany(@RequestBody CompanyDto company) throws Exception {
        try {
            return ResponseEntity.ok(companyService.saveCompany(company));
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{id}/mails")
    public ResponseEntity<List<MailDto>> getMailsByCompanyId(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(companyService.getMailByCompany(id));
        }catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }
}
