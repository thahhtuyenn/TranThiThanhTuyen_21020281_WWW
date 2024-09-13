package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import vn.edu.iuh.fit.thanhtuyen.labweek1.Connect;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.RoleRepository;

import java.util.List;

public class RoleRepositoryImpl implements RoleRepository{
    @Override
    public List<Role> findAll() {
        try (EntityManager em = Connect.getEntityManager()){
            return em.createNamedQuery("Role.findAll", Role.class).getResultList();
        }
    }
}
