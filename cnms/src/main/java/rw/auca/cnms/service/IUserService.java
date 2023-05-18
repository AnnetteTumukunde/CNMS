package rw.auca.cnms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.auca.cnms.model.Users;

import java.util.List;

public interface IUserService {
    public Users createUser(Users users);
    public Users updateUser(Users users, Long id);
    public void deleteUser(Long id);
    public List<Users> getUsers();
    public Users getUser(Long id);
    public Users loginUser(String email, String password);
    Page<Users> getPaginatedUsers(Pageable pageable);
    List<Users> searchByName(String searchName);
}
