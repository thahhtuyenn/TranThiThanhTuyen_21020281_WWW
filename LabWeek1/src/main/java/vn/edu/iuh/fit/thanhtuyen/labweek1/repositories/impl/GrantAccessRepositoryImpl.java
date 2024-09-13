package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.thanhtuyen.labweek1.Connect;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.GrantAccess;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.GrantAccessRepository;

import java.util.List;

public class GrantAccessRepositoryImpl implements GrantAccessRepository {
    @Override
    public GrantAccess findByAccountIdAndRoleId(String accountId, String roleId) {
        try (EntityManager em = Connect.getEntityManager()){
            List<GrantAccess> grantAccesses = em.createNamedQuery("GrantAccess.findByAccountIdAndRoleId", GrantAccess.class)
                    .setParameter("accountId", accountId)
                    .setParameter("roleId", roleId)
                    .getResultList();
            return grantAccesses.isEmpty() ? null : grantAccesses.get(0);

        }
    }

    @Override
    public GrantAccess save(GrantAccess grantAccess) {
        GrantAccess grantTemp = findByAccountIdAndRoleId(grantAccess.getId().getRoleId(), grantAccess.getId().getAccountId());
        if (grantTemp == null) {
            try(var em = Connect.getEntityManager()) {
                em.getTransaction().begin();
                String query = "INSERT INTO grant_access (account_id, role_id, is_grant) VALUES (?, ?, ?)";
                em.createNativeQuery(query)
                        .setParameter(1, grantAccess.getId().getAccountId())
                        .setParameter(2, grantAccess.getId().getRoleId())
                        .setParameter(3, (byte)1)
                        .executeUpdate();
                em.getTransaction().commit();
                return grantAccess;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try(var em = Connect.getEntityManager()) {
                em.getTransaction().begin();
                String query = "UPDATE grant_access SET note = ?, account_id = ?, role_id = ? WHERE account_id = ? AND role_id = ?";
                em.createNativeQuery(query)
                        .setParameter(1, grantAccess.getNote())
                        .setParameter(2, grantAccess.getId().getAccountId())
                        .setParameter(3, grantAccess.getId().getRoleId())
                        .setParameter(4, grantAccess.getId().getAccountId())
                        .setParameter(5, grantAccess.getId().getRoleId())
                        .executeUpdate();
                em.getTransaction().commit();
                return grantAccess;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return grantTemp;
    }
}
