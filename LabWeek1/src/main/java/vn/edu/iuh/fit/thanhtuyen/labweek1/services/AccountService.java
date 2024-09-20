package vn.edu.iuh.fit.thanhtuyen.labweek1.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.GrantAccess;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.GrantAccessId;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.AccountRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.LogRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl.AccountRepositoryImpl;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl.LogRepositoryImpl;

import java.util.List;

public class AccountService {
    @Inject
    private AccountRepository accountRepostory;

    @Inject
    private LogRepository logRepository;

    @Inject
    private GrantAccessRepository grantAccessRepository;


    /**
     * Login
     * @param accountId
     * Khi account login, thì lưu thời gian login
     */
    public void login(String accountId) {
        logRepository.saveTimeLogin(accountId);
    }

    /**
     * Logout
     * @param accountId
     * Khi account logout, thì lưu thời gian logout
     */
    public void logout(String accountId) {
        logRepository.saveTimeLogout(accountId);
    }

    /**
     * Tim kiem account theo accountId va password
     * @param  accountId: id cua account can tim
     *         password: mat khau cua account can tim
     * @return account tim duoc hoac null neu khong tim thay
     */
    public Account findByAccountIdAndPassword(String accountId, String password) {
        Account account = accountRepostory.findByAccountIdAndPassword(accountId, password);
        return account;
    }

    /**
     * Tim kiem account theo accountId
     * @param accountId: id cua account can tim
     * @return account tim duoc hoac null neu khong tim thay
     */
    public Account findByAccountId(String username) {
        Account account = accountRepostory.findByAccountId(username);
        return account;
    }

    /**
     * Lay ra danh sach account khong phai la admin va co status = 1
     */
    public List<Account> findAccountNotIsAdmin() {
        List<Account> accounts = accountRepostory.findAccountNotIsAdmin();
        return accounts;
    }

    /**
     * Them moi account hoac cap nhat account
     * - Neu account da ton tai thi cap nhat
     * - Neu account chua ton tai thi them moi
     * @param account: account can them moi hoac cap nhat
     * @return account sau khi them moi hoac cap nhat
     */
    public  Account save(Account account) {
        account = accountRepostory.save(account);
        String accountId = account.getAccountId();
        System.out.println(account.getGrantAccesses());
        account.getGrantAccesses().forEach(g -> {
            GrantAccessId grantAccessId = new GrantAccessId();
            grantAccessId.setAccountId(accountId);
            grantAccessId.setRoleId(g.getRole().getRoleId());
            g.setId(grantAccessId);

            Account accountTemp = new Account();
            accountTemp.setAccountId(accountId);
            g.setAccount(accountTemp);
            Role role = new Role();
            role.setRoleId(g.getRole().getRoleId());
            g.setRole(role);
            grantAccessRepository.save(g);
        });
        return account;
    }

    public boolean delete(String accountId) {
        Account account = accountRepostory.findByAccountId(accountId);
        if (account != null) {
            accountRepostory.delete(account.getAccountId());
            return true;
        }
        return false;
    }
}
