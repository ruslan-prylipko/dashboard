package ua.com.dashboard.services.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.com.dashboard.repositories.user.role.RoleRepository;
import ua.com.dashboard.view.user.role.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

}
