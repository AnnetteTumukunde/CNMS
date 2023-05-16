package rw.auca.cnms.service;

import rw.auca.cnms.model.Role;

import java.util.List;

public interface IRoleService {
    public Role createRole(Role role);
    public Role updateRole(Role role, Long id);
    public void deleteRole(Long id);
    public List<Role> getRoles();
    public Role getRole(Long id);
}
