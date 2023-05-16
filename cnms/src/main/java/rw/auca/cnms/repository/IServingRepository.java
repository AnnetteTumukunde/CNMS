package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Serving;

@Repository
public interface IServingRepository extends JpaRepository<Serving, Long> {
}
