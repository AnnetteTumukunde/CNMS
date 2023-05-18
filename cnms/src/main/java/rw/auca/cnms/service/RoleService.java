package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.cnms.model.Role;
import rw.auca.cnms.repository.IRoleRepository;

import java.util.Date;
import java.util.List;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role, Long id) {
        Role existingRole = roleRepository.findById(id).orElse(null);
        if (existingRole != null) {
            Role updatedRole = new Role();
            updatedRole.setId(existingRole.getId());
            updatedRole.setName(role.getName() != null ? role.getName() : existingRole.getName());
            return roleRepository.save(updatedRole);
        }
        return null;
    }

    @Override
    public void deleteRole(Long id) {
        Role existingRole = roleRepository.findById(id).orElse(null);
        if (existingRole != null) {
            roleRepository.delete(existingRole);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
