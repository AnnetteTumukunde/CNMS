package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Users;

import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String email);
}
