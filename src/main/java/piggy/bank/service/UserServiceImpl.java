package piggy.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piggy.bank.entity.User;
import piggy.bank.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameOrEmail(String usernameOrEmail) {
        User user = null;
        try {
            user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

}