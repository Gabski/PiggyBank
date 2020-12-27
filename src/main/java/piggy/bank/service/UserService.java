package piggy.bank.service;

import piggy.bank.entity.User;

public interface UserService {
    User findByUsernameOrEmail(String usernameOrEmail);

}