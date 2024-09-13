package vn.edu.iuh.fit.thanhtuyen.labweek1.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "grant_access")
@NamedQueries({
        @NamedQuery(name = "GrantAccess.findByAccountIdAndRoleId",
                query = "SELECT ga FROM GrantAccess ga WHERE ga.id.accountId = :accountId AND ga.id.roleId = :roleId")
})
public class GrantAccess {
    @EmbeddedId
    private GrantAccessId id;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "is_grant", nullable = false)
    private Boolean isGrant = false;

    @Column(name = "note", length = 250)
    private String note;

    public GrantAccessId getId() {
        return id;
    }

    public void setId(GrantAccessId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Boolean isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}