package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.cnms.model.Serving;
import rw.auca.cnms.repository.IServingRepository;

import java.util.Date;
import java.util.List;

@Service
public class ServingService implements IServingService{

    @Autowired
    private IServingRepository servingRepository;

    @Override
    public Serving createServing(Serving serving) {
        serving.setCreationDate(new Date());
        serving.setUpdateDate(new Date());
        serving.setVer(0);
        return servingRepository.save(serving);
    }

    @Override
    public Serving updateServing(Serving serving, Long id) {
        Serving existingServing = servingRepository.findById(id).orElse(null);
        if (existingServing != null) {
            Serving updatedServing = new Serving();
            updatedServing.setId(existingServing.getId());
            updatedServing.setChildGrowth(serving.getChildGrowth() != null ? serving.getChildGrowth() : existingServing.getChildGrowth());
            updatedServing.setNutrition(serving.getNutrition() != null ? serving.getNutrition() : existingServing.getNutrition());
            updatedServing.setPreferredTime(serving.getPreferredTime() != null ? serving.getPreferredTime() : existingServing.getPreferredTime());
            updatedServing.setQuantity(serving.getQuantity() != null ? serving.getQuantity() : existingServing.getQuantity());
            updatedServing.setQuantityType(serving.getQuantityType() != null ? serving.getQuantityType() : existingServing.getQuantityType());
            updatedServing.setUpdateDate(new Date());
            Integer version = existingServing.getVer();
            updatedServing.setVer(version++);
            return servingRepository.save(updatedServing);
        }
        return null;
    }

    @Override
    public void deleteServing(Long id) {
        Serving existingServing = servingRepository.findById(id).orElse(null);
        if (existingServing != null) {
            servingRepository.delete(existingServing);
        }
    }

    @Override
    public List<Serving> findAllServing() {
        return servingRepository.findAll();
    }

    @Override
    public Serving findOneServing(Long id) {
        return servingRepository.findById(id).orElse(null);
    }
}
