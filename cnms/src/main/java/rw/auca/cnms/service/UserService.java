package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rw.auca.cnms.model.Role;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.repository.IRoleRepository;
import rw.auca.cnms.repository.IUsersRepository;

import java.util.*;
import java.util.stream.Collectors;

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
                adminRole.setName("ADMIN");
                roleService.createRole(adminRole);
                users.setRole(Arrays.asList(adminRole));
            }
        } else {
            if (roleService.getRoles().size() == 1) {
                Role userRole = new Role();
                userRole.setName("USER");
                roleService.createRole(userRole);
                users.setRole(Arrays.asList(userRole));
            } else {
                users.setRole(Arrays.asList(roleService.getRoles().get(0)));
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
//            updatedUser.setUsername(users.getUsername() != null ? users.getUsername() : existingUser.getUsername());
            updatedUser.setRole(users.getRole() != null ? users.getRole() : existingUser.getRole());
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
        Users users = usersRepository.findByEmail(email);
        if (users.getPassword().equals(bCryptPasswordEncoder.encode(password).toString())) {
            return usersRepository.findById(users.getId()).orElse(null);
        }
        return null;
    }

    @Override
    public Page<Users> getPaginatedUsers(Pageable pageable) {
        List<Users> allUsers = getUsers();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allUsers.size());

        return new PageImpl<>(allUsers.subList(start, end), pageable, allUsers.size());
    }

    @Override
    public List<Users> searchByName(String searchName) {
        String uname = searchName;
        List<Users> allUsers = usersRepository.searchByUserName(uname);
        return allUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);
        mapRolesToAuthorities(user.getRole());
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
