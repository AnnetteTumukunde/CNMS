package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Child;

@Repository
public interface IChildRepository extends JpaRepository<Child, Long> {
}
