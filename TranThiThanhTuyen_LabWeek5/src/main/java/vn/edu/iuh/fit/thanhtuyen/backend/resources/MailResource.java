package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.JobService;

@RestController
@RequestMapping("/api/mails")
public class MailResource {
    @Autowired
    private JobService jobService;

    @PostMapping("/sendMail")
    public ResponseEntity<Boolean> sendMail(@RequestBody MailDto mailDto){
        try {
            boolean result = jobService.sendMail(mailDto);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }
}
