package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import piggy.bank.entity.Account;
import piggy.bank.entity.Role;
import piggy.bank.entity.User;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.user=:user")
    List<Account> getAllByUser(User user);
}