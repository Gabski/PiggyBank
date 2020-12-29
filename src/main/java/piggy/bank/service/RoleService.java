package piggy.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piggy.bank.domain.AuthorityType;
import piggy.bank.entity.Role;
import piggy.bank.repository.RoleRepository;


@Service("roleService")
public class RoleService implements RoleServiceInterface {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role getAdminRole() {
        return findByName(AuthorityType.ROLE_ADMIN.name());
    }

    @Override
    public Role getUserRole() {
        return findByName(AuthorityType.ROLE_USER.name());
    }
}