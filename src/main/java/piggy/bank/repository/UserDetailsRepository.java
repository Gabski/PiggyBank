package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piggy.bank.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}