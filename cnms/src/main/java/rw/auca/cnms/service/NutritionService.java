package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.repository.INutritionRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class NutritionService implements INutritionService{

    @Autowired
    private INutritionRepository nutritionRepository;

    @Override
    public Nutrition registerNutrition(Nutrition nutrition, MultipartFile pictureFile) {
        nutrition.setCreationDate(new Date());
        nutrition.setUpdateDate(new Date());
        nutrition.setVer(0);
        if (!pictureFile.isEmpty()) {
            String contentType = pictureFile.getContentType();
            if (contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("application/pdf")) {
                try {
                    byte[] picture = pictureFile.getBytes();
                    nutrition.setPicture(picture);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Only JPEG, PNG, and PDF files are allowed.");
            }
        }
        return nutritionRepository.save(nutrition);
    }

    @Override
    public Nutrition updateNutrition(Nutrition nutrition, Long id) {
        Nutrition existingNutrition = nutritionRepository.findById(id).orElse(null);
        if (existingNutrition != null) {
            Nutrition updatedNutrition = new Nutrition();
            updatedNutrition.setId(existingNutrition.getId());
            updatedNutrition.setName(nutrition.getName() != null ? nutrition.getName() : existingNutrition.getName());
            updatedNutrition.setType(nutrition.getType() != null ? nutrition.getType() : existingNutrition.getType());
            updatedNutrition.setDescription(nutrition.getDescription() != null ? nutrition.getDescription() : existingNutrition.getDescription());
            updatedNutrition.setUpdateDate(new Date());
            Integer version = existingNutrition.getVer();
            updatedNutrition.setVer(version++);
            return nutritionRepository.save(updatedNutrition);
        }
        return null;
    }

    @Override
    public void deleteNutrition(Long id) {
        Nutrition existingNutrition = nutritionRepository.findById(id).orElse(null);
        if (existingNutrition != null) {
            nutritionRepository.delete(existingNutrition);
        }
    }

    @Override
    public List<Nutrition> findAllNutritions() {
        return nutritionRepository.findAll();
    }

    @Override
    public Nutrition findOneNutrition(Long id) {
        return nutritionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Nutrition> getPaginatedNutritions(Pageable pageable) {
        List<Nutrition> allNutritions = findAllNutritions();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allNutritions.size());

        return new PageImpl<>(allNutritions.subList(start, end), pageable, allNutritions.size());
    }
}
