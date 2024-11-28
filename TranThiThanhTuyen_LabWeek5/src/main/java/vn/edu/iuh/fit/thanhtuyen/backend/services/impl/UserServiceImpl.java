package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.UserMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.User;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.UserRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto loadByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDto(user);
    }
}
