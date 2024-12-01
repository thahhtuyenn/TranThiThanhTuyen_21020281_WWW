package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserRegisterDto;

public interface UserModel {
    UserDto loadByUsername(String username);
    boolean register(UserRegisterDto userRegisterDto);
}
