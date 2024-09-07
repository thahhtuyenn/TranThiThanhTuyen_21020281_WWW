package vn.edu.iuh.fit.thanhtuyen.labweek1.services;

import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.AccountRepostory;

public class AccountService {
    private AccountRepostory accountRepostory;
    public AccountService() {
        accountRepostory = new AccountRepostory();
    }
    public boolean checkLogin(String username, String password) {
        Account account = accountRepostory.findByAccountIdAndPassword(username, password);
        return account != null;
    }
}
