package rw.auca.cnms.service;

import rw.auca.cnms.model.Users;

import java.util.List;

public interface IUserService {
    public Users createUser(Users users);
    public Users updateUser(Users users, Long id);
    public void deleteUser(Long id);
    public List<Users> getUsers();
    public Users getUser(Long id);
    public Users loginUser(String email, String password);
}
