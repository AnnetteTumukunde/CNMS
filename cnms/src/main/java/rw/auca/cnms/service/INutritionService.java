package rw.auca.cnms.service;

import rw.auca.cnms.model.Nutrition;

import java.util.List;

public interface INutritionService {
    public Nutrition registerNutrition(Nutrition nutrition);
    public Nutrition updateNutrition(Nutrition nutrition, Long id);
    public void deleteNutrition(Long id);
    public List<Nutrition> findAllNutritions();
    public Nutrition findOneNutrition(Long id);
}
