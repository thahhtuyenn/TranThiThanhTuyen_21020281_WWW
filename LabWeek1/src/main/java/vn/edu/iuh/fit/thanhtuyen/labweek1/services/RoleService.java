package vn.edu.iuh.fit.thanhtuyen.labweek1.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.RoleRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl.RoleRepositoryImpl;

import java.util.List;

public class RoleService {

    @Inject
    private RoleRepository roleRepository;

    /**
     * Lay danh sach Role
     *
     * @return List<Role>
     */
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
