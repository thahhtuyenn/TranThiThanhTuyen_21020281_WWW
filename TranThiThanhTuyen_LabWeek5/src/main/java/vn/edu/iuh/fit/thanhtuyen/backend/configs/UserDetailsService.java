package vn.edu.iuh.fit.thanhtuyen.backend.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.frontend.models.UserModel;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserModel userModel;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userModel.loadByUsername(username);
        if(userDto == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new vn.edu.iuh.fit.thanhtuyen.backend.configs.UserDetails(userDto);
    }
}
