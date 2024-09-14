package vn.edu.iuh.fit.thanhtuyen.labweek1.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.AccountRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.LogRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl.AccountRepositoryImpl;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl.LogRepositoryImpl;

import java.util.List;

public class AccountService {
    @Inject
    private AccountRepository accountRepostory;

    @Inject
    private LogRepository logRepository;


    public void login(String accountId){
        logRepository.saveTimeLogin(accountId);
    }

    public void logout(String accountId){
        logRepository.saveTimeLogout(accountId);
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
