package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String fullName;
    private boolean company;
}
