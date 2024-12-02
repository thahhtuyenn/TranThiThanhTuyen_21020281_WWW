package vn.edu.iuh.fit.thanhtuyen.backend.services;

import jakarta.mail.MessagingException;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;

public interface MailService {
    void senderForCandidate(Company company, String content, String to, String subject) throws MessagingException;

}
