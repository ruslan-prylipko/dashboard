package ua.com.dashboard.repositories.user;

import org.springframework.data.repository.CrudRepository;
import ua.com.dashboard.view.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
