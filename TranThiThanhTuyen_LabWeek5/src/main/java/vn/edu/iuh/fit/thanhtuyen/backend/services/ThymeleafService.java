package vn.edu.iuh.fit.thanhtuyen.backend.services;

import java.util.Map;

public interface ThymeleafService {
    public String createContent(String template, Map<String, Object> variables);
}
