package vn.edu.iuh.fit.thanhtuyen.labweek1.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.AccountRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl.AccountRepositoryImpl;

import java.util.List;

public class AccountService {
    @Inject
    private AccountRepository accountRepostory;

    public AccountService() {
        accountRepostory = new AccountRepositoryImpl();
    }
    public Account findByAccountIdAndPassword(String username, String password) {
        Account account = accountRepostory.findByAccountIdAndPassword(username, password);
        return account;
    }

    public Account findByAccountId(String username) {
        Account account = accountRepostory.findByAccountId(username);
        return account;
    }

    public List<Account> findAccountNotIsAdmin(){
        List<Account> accounts = accountRepostory.findAccountNotIsAdmin();
        return accounts;
    }
}
