package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.model.ChildGrowth;
import rw.auca.cnms.model.EBMIStatus;
import rw.auca.cnms.repository.IChildGrowthRepository;
import rw.auca.cnms.repository.IChildRepository;

import java.util.Date;
import java.util.List;

@Service
public class ChildGrowthService implements IChildGrowthService{

    @Autowired
    private IChildGrowthRepository childGrowthRepository;

    @Override
    public ChildGrowth addChildGrowth(ChildGrowth childGrowth) {
        calculateBMI(childGrowth);
        childGrowth.setCreationDate(new Date());
        childGrowth.setUpdateDate(new Date());
        childGrowth.setVer(0);
        return childGrowthRepository.save(childGrowth);
    }

    @Override
    public ChildGrowth updateChildGrowth(ChildGrowth childGrowth, Long id) {
        ChildGrowth existingChildGrowth = childGrowthRepository.findById(id).orElse(null);
        if (existingChildGrowth != null) {
            ChildGrowth updatedChildGrowth = new ChildGrowth();
            updatedChildGrowth.setId(existingChildGrowth.getId());
            updatedChildGrowth.setChild(childGrowth.getChild() != null ? childGrowth.getChild() : existingChildGrowth.getChild());
            updatedChildGrowth.setHeight(childGrowth.getHeight() != null ? childGrowth.getHeight() : existingChildGrowth.getHeight());
            updatedChildGrowth.setWeight(childGrowth.getWeight() != null ? childGrowth.getWeight() : existingChildGrowth.getWeight());
            updatedChildGrowth.setDateOfRecord(childGrowth.getDateOfRecord() != null ? childGrowth.getDateOfRecord() : existingChildGrowth.getDateOfRecord());
            calculateBMI(updatedChildGrowth);
            updatedChildGrowth.setComment(childGrowth.getComment() != null ? childGrowth.getComment() : existingChildGrowth.getComment());
            updatedChildGrowth.setUpdateDate(new Date());
            Integer version = existingChildGrowth.getVer();
            updatedChildGrowth.setVer(version++);
            return childGrowthRepository.save(updatedChildGrowth);
        }
        return null;
    }

    public void calculateBMI(ChildGrowth childGrowth) {
        Double bmi = childGrowth.getWeight() / (childGrowth.getHeight() * childGrowth.getHeight());
        if (bmi < 18.5) {
            childGrowth.setBmi(bmi);
            childGrowth.setEbmiStatus(EBMIStatus.UNDER_WEIGHT);
        } else if (bmi >= 18.5 && bmi < 25) {
            childGrowth.setBmi(bmi);
            childGrowth.setEbmiStatus(EBMIStatus.NORMAL);
        } else if (bmi >= 25 && bmi < 30) {
            childGrowth.setBmi(bmi);
            childGrowth.setEbmiStatus(EBMIStatus.OVER_WEIGHT);
        } else {
            childGrowth.setBmi(bmi);
            childGrowth.setEbmiStatus(EBMIStatus.OBESE);
        }
    }

    @Override
    public void deleteChildGrowth(Long id) {
        ChildGrowth existingChildGrowth = childGrowthRepository.findById(id).orElse(null);
        if (existingChildGrowth != null) {
            childGrowthRepository.delete(existingChildGrowth);
        }
    }

    @Override
    public List<ChildGrowth> findAllChildGrowth() {
        return childGrowthRepository.findAll();
    }

    @Override
    public ChildGrowth findOneChildGrowth(Long id) {
        return childGrowthRepository.findById(id).orElse(null);
    }
}
