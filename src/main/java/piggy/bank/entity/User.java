package piggy.bank.entity;

import piggy.bank.entity.Role;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "username", length = 65)
    private String username;

    @NotNull
    @NotEmpty
    @Column(name = "password", length = 64)
    private String password;

    @NotNull
    @NotEmpty
    @Column(name = "email", unique = true, length = 115)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDetails_id")
    private UserDetails userDetails;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "accounts",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @OrderBy("createAt")
    private final Set<Account> accounts = new HashSet<>();


    public Set<Account> getAccounts() {
        return accounts;
    }

    public User() {
        userDetails = new UserDetails();
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}