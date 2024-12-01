package vn.edu.iuh.fit.thanhtuyen.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserRegisterDto;
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

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        try {
            boolean result = userService.registerUser(userRegisterDto);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            throw new Exception  ("Error: " + e.getMessage(), e);
        }
    }
}
