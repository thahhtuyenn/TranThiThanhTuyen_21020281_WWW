package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.UserRegisterDto;
import vn.edu.iuh.fit.thanhtuyen.backend.mappers.UserMapper;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Company;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Role;
import vn.edu.iuh.fit.thanhtuyen.backend.models.User;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.RoleRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.UserRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto loadByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDto(user);
    }

    @Override
    public boolean registerUser(UserRegisterDto userRegisterDto) {
        User user = userRepository.findByUsername(userRegisterDto.getUsername());
        if (user != null){
            return false;
        }
        if(!userRegisterDto.getPassword().equalsIgnoreCase(userRegisterDto.getConfirmPassword())){
            return false;
        }
        boolean isCompany = userRegisterDto.isCompany();
        String pw = passwordEncoder.encode(userRegisterDto.getPassword());
        if (isCompany){
            Optional<Role> roles = roleRepository.findByCode("COMPANY");
            if(!roles.isPresent()){
                return false;
            }
            Company company = Company.builder()
                    .compName(userRegisterDto.getFullName())
                    .email(userRegisterDto.getEmail())
                    .phone(userRegisterDto.getPhone())
                    .username(userRegisterDto.getUsername())
                    .password(pw)
                    .roles(List.of(roles.get()))
                    .build();
            company = companyRepository.save(company);
            return company != null;
        } else {
            Optional<Role> roles = roleRepository.findByCode("CANDIDATE");
            if(!roles.isPresent()){
                return false;
            }
            Candidate candidate = Candidate.builder()
                    .fullName(userRegisterDto.getFullName())
                    .email(userRegisterDto.getEmail())
                    .phone(userRegisterDto.getPhone())
                    .username(userRegisterDto.getUsername())
                    .password(pw)
                    .roles(List.of(roles.get()))
                    .build();
            candidate = candidateRepository.save(candidate);
            return candidate != null;
        }
    }
}
