package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Serving;
import rw.auca.cnms.model.Users;

import java.util.List;

@Repository
public interface IServingRepository extends JpaRepository<Serving, Long> {
}
