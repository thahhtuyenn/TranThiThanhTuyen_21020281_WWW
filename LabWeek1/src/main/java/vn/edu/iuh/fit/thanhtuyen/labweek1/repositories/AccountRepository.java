package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;

import java.util.List;

public interface AccountRepository {
    Account findByAccountIdAndPassword(String accountId, String password);
    Account findByAccountId(String accountId);
    List<Account> findAccountNotIsAdmin();
    Account save(Account account);
    boolean delete(String accountId);
}
