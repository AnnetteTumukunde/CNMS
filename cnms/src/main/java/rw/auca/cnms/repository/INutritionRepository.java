package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.model.Users;

import java.util.List;

@Repository
public interface INutritionRepository extends JpaRepository<Nutrition, Long> {
    @Query("SELECT a FROM Nutrition a WHERE a.type LIKE ?1")
    List<Nutrition> searchByName(String name);
}
