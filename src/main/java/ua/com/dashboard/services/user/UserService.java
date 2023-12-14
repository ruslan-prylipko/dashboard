package ua.com.dashboard.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.dashboard.view.user.User;

public interface UserService extends UserDetailsService {
    User save(User user);
    User findByUsername(String username);
}
