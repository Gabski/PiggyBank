package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piggy.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}