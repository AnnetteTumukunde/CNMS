package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.ChildGrowth;

@Repository
public interface IChildGrowthRepository extends JpaRepository<ChildGrowth, Long> {
}
