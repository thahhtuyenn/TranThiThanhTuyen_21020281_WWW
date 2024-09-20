package vn.edu.iuh.fit.thanhtuyen.labweek1.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findByAccountIdAndPassword", query = "select a from Account a where a.accountId = :accountId and a.password = :password"),
        @NamedQuery(name = "Account.findByAccountId", query = "select a from Account a where a.accountId = :accountId"),
        @NamedQuery(name = "Account.findAccountNotIsAdmin", query = "select a from Account a where a.accountId not in (select ga.id.accountId from GrantAccess ga where ga.id.roleId = 'admin') AND a.status = 1"),
})
public class Account {
    @Id
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

    public Account() {
    }

    public Account(String accountId, String fullName, String password, String email, String phone, Byte status, Set<GrantAccess> grantAccesses) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.grantAccesses = grantAccesses;
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Set<GrantAccess> getGrantAccesses() {
        return grantAccesses;
    }

    public void setGrantAccesses(Set<GrantAccess> grantAccesses) {
        this.grantAccesses = grantAccesses;
    }

    /**
     * Check if the account has a role
     * @param roleId
     * @return
     */
    public boolean isRole(String roleId) {
        for (GrantAccess ga : grantAccesses) {
            if (ga.getRole().getRoleId().equals(roleId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", grantAccesses=" + grantAccesses +
                '}';
    }
}