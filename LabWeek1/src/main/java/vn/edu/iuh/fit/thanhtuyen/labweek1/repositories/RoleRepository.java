package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();
}
