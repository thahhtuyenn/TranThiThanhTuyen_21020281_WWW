package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.GrantAccess;

public interface GrantAccessRepository {
    GrantAccess findByAccountIdAndRoleId(String accountId, String roleId);
    GrantAccess save(GrantAccess grantAccess);
}
