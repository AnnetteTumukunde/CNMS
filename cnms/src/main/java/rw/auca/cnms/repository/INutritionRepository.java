package rw.auca.cnms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.cnms.model.Nutrition;

@Repository
public interface INutritionRepository extends JpaRepository<Nutrition, Long> {
}
