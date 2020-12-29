package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piggy.bank.entity.Currency;
import piggy.bank.entity.Role;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}