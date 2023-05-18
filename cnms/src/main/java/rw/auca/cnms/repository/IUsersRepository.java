package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    @Query("SELECT a FROM Users a WHERE a.email LIKE ?1")
    List<Users> searchByUserName(String searchName);
}
