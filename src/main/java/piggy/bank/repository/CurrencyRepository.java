package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import piggy.bank.entity.Currency;
import piggy.bank.entity.Role;
import piggy.bank.entity.User;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("SELECT c FROM Currency c WHERE c.slug=:slug")
    Currency findBySlug(String slug);
}