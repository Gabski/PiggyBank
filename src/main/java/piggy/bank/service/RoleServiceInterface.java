package piggy.bank.service;

import piggy.bank.entity.Role;

public interface RoleServiceInterface {
    Role findByName(String name);

    Role getAdminRole();

    Role getUserRole();
}