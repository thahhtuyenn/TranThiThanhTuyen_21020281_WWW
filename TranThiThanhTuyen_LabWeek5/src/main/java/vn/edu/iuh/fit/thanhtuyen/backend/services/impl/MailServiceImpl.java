package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;
import vn.edu.iuh.fit.thanhtuyen.backend.services.MailService;
import vn.edu.iuh.fit.thanhtuyen.backend.services.ThymeleafService;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private ThymeleafService thymeleafService;

    @Override
    public void senderForCandidate(Company company, String content, String to, String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(to);
        helper.setSubject(subject);

        // content
        Map<String, Object> variables = new HashMap<>();
        variables.put("content", content);
        variables.put("phone", company.getPhone());
        variables.put("email", company.getEmail());
        variables.put("webUrl", company.getWebUrl());
        variables.put("compName", company.getCompName());

        helper.setText(thymeleafService.createContent("init-mail.html", variables), true);
        helper.setFrom(email);
        mailSender.send(message);
    }
}
