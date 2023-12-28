package ua.com.dashboard.repositories.user.role;

import org.springframework.data.repository.CrudRepository;

import ua.com.dashboard.view.user.role.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
