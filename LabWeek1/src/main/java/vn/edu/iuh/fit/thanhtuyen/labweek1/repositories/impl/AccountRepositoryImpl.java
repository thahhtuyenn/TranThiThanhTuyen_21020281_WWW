package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.thanhtuyen.labweek1.Connect;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.AccountRepository;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    /**
     * Find account by accountId and password
     *
     * @param accountId
     * @param password
     * @return Account if found, null if not found
     */
    public Account findByAccountIdAndPassword(String accountId, String password) {
        try (EntityManager em = Connect.getEntityManager()) {
            List<Account> accounts = em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                    .setParameter("accountId", accountId)
                    .setParameter("password", password)
                    .getResultList();
            return !accounts.isEmpty() ? accounts.get(0) : null;
        }
    }

    /**
     * Find account by accountId
     *
     * @param accountId
     * @return Account if found, null if not found
     */
    @Override
    public Account findByAccountId(String accountId) {
        try (EntityManager em = Connect.getEntityManager()) {
            List<Account> accounts = em.createNamedQuery("Account.findByAccountId", Account.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
            return accounts.isEmpty() ? null : accounts.get(0);

        }
    }

    /**
     * Find all account not is admin
     *
     * @return List<Account> not is admin, status = 1
     */
    @Override
    public List<Account> findAccountNotIsAdmin() {
        try (EntityManager em = Connect.getEntityManager()) {
            return em.createNamedQuery("Account.findAccountNotIsAdmin", Account.class)
                    .getResultList();
        }
    }

    /**
     * Save account
     *  - If account is not exist, insert new account
     *  - If account is exist, update account
     * @param account: information of account need to add or update
     * @return account
     */
    @Override
    public Account save(Account account) {
        try (EntityManager em = Connect.getEntityManager()) {
            em.getTransaction().begin();

            Account accountId = em.find(Account.class, account.getAccountId());
            if(accountId == null){
                em.persist(account);
            }else {
                String query = "UPDATE account SET  full_name = ?, email = ?, phone = ? WHERE account_id = ?";
                em.createNativeQuery(query)
                        .setParameter(1, account.getFullName())
                        .setParameter(2, account.getEmail())
                        .setParameter(3, account.getPhone())
                        .setParameter(4, account.getAccountId())
                        .executeUpdate();
            }

            em.getTransaction().commit();
            return account;
        }
    }

    @Override
    public boolean delete(String accountId) {
        try (EntityManager em = Connect.getEntityManager()) {
            Account account = em.find(Account.class, accountId);
            if (account != null) {
                account.setStatus((byte) 0);
                em.getTransaction().begin();
                em.merge(account);
                em.getTransaction().commit();
                return true;
            }
            return false;
        }

    }
}
