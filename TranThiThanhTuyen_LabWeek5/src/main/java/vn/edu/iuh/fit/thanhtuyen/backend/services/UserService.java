package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserRegisterDto;

public interface UserService {
    UserDto loadByUsername(String username);
    boolean registerUser(UserRegisterDto userRegisterDto);
}
