package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rw.auca.cnms.model.Role;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.repository.IRoleRepository;
import rw.auca.cnms.repository.IUsersRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users createUser(Users users) {
        if (usersRepository.findAll().isEmpty()) {
            if (roleService.getRoles().isEmpty()) {
                Role adminRole = new Role();
                adminRole.setCreationDate(new Date());
                adminRole.setUpdateDate(new Date());
                adminRole.setVer(0);
                adminRole.setName("ADMIN");
                roleService.createRole(adminRole);
                users.setRoles(Arrays.asList(adminRole));
            }
        } else {
            if (roleService.getRoles().size() == 1) {
                Role userRole = new Role();
                userRole.setCreationDate(new Date());
                userRole.setUpdateDate(new Date());
                userRole.setVer(0);
                userRole.setName("USER");
                roleService.createRole(userRole);
                users.setRoles(Arrays.asList(userRole));
            }
        }
        users.setCreationDate(new Date());
        users.setUpdateDate(new Date());
        users.setVer(0);
        users.setState("active");
        String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        return usersRepository.save(users);
    }

    @Override
    public Users updateUser(Users users, Long id) {
        Users existingUser = usersRepository.findById(id).orElse(null);
        if (existingUser != null) {
            Users updatedUser = new Users();
            updatedUser.setId(existingUser.getId());
            updatedUser.setState(existingUser.getState());
            updatedUser.setEmail(users.getEmail() != null ? users.getEmail() : existingUser.getEmail());
            updatedUser.setPhoneNumber(users.getPhoneNumber() != null ? users.getPhoneNumber() : existingUser.getPhoneNumber());
            updatedUser.setUsername(users.getUsername() != null ? users.getUsername() : existingUser.getUsername());
            updatedUser.setRoles(users.getRoles() != null ? users.getRoles() : existingUser.getRoles());
            updatedUser.setPassword(users.getPassword() != null ? users.getPassword() : existingUser.getPassword());
            updatedUser.setUpdateDate(new Date());
            Integer version = existingUser.getVer();
            updatedUser.setVer(version++);
            return usersRepository.save(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        Users existingUser = usersRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setState("INACTIVE");
            usersRepository.save(existingUser);
        }
    }

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public Users loginUser(String email, String password) {
        Optional<Users> users = usersRepository.findByUsername(email);
        if (users.get().getPassword().equals(password)) {
            return usersRepository.findById(users.get().getId()).orElse(null);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) usersRepository.findByUsername(email).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", email)
                ));
    }
}
