package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.thanhtuyen.labweek1.Connect;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;

import java.util.List;

public class AccountRepostory extends Connect {
    public Account findByAccountIdAndPassword(String accountId, String password) {
        try(EntityManager em = getEntityManager()){
            List<Account> accounts = em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                    .setParameter("accountId", accountId)
                    .setParameter("password", password)
                    .getResultList();
            return accounts.size() > 0 ? accounts.get(0) : null;
        }
    }
}
