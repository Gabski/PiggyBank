package piggy.bank.service;

import piggy.bank.entity.User;

public interface UserServiceInterface {
    User findByUsernameOrEmail(String usernameOrEmail);
}