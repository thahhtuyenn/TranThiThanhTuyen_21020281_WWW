package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.thanhtuyen.labweek1.Connect;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.AccountRepository;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    public Account findByAccountIdAndPassword(String accountId, String password) {
        try(EntityManager em = Connect.getEntityManager()){
            List<Account> accounts = em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                    .setParameter("accountId", accountId)
                    .setParameter("password", password)
                    .getResultList();
            return !accounts.isEmpty() ? accounts.get(0) : null;
        }
    }

    @Override
    public Account findByAccountId(String accountId) {
        try (EntityManager em = Connect.getEntityManager()){
            List<Account> accounts = em.createNamedQuery("Account.findByAccountId", Account.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
            return accounts.isEmpty() ? null : accounts.get(0);

        }
    }

    @Override
    public List<Account> findAccountNotIsAdmin() {
        try (EntityManager em = Connect.getEntityManager()){
            return em.createNamedQuery("Account.findAccountNotIsAdmin", Account.class)
                    .getResultList();
        }
    }
}
