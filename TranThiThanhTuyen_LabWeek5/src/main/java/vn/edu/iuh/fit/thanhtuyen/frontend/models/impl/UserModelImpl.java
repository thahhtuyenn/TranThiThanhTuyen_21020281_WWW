package vn.edu.iuh.fit.thanhtuyen.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.UserModel;

@Component
public class UserModelImpl implements UserModel {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDto loadByUsername(String username) {
        String url = "http://localhost:9623/api/users?username=" + username;
        ResponseEntity<UserDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserDto>() {
                }
        );
        return response.getBody();
    }
}
