package rw.auca.cnms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.model.Users;

import java.util.List;

public interface INutritionService {
    public Nutrition registerNutrition(Nutrition nutrition, MultipartFile pictureFile);
    public Nutrition updateNutrition(Nutrition nutrition, Long id);
    public void deleteNutrition(Long id);
    public List<Nutrition> findAllNutritions();
    public Nutrition findOneNutrition(Long id);
    Page<Nutrition> getPaginatedNutritions(Pageable pageable);
}
