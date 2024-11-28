package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;

public interface UserService {
    UserDto loadByUsername(String username);
}
