package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import piggy.bank.entity.Account;
import piggy.bank.entity.Role;

public interface AccountRepository extends JpaRepository<Account, Long> {
}