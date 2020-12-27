package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piggy.bank.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}