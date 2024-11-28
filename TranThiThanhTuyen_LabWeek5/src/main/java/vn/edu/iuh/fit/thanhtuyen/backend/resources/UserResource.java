package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) throws Exception {
        try {
            UserDto user = userService.loadByUsername(username);
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }
}
