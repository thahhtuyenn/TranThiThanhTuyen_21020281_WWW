package vn.edu.iuh.fit.thanhtuyen.frontend.models;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;

public interface UserModel {
    UserDto loadByUsername(String username);
}
