package rw.auca.cnms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.repository.IChildRepository;

import java.util.Date;
import java.util.List;

@Service
public class ChildService implements IChildService{

    @Autowired
    private IChildRepository childRepository;

    @Override
    public Child registerChild(Child child) {
        child.setCreationDate(new Date());
        child.setUpdateDate(new Date());
        child.setVer(0);
        return childRepository.save(child);
    }

    @Override
    public Child updateChild(Child child, Long id) {
        Child existingChild = childRepository.findById(id).orElse(null);
        if (existingChild != null) {
            Child updatedChild = new Child();
            updatedChild.setId(existingChild.getId());
            updatedChild.setName(child.getName() != null ? child.getName() : existingChild.getName());
            updatedChild.setGender(child.getGender() != null ? child.getGender() : existingChild.getGender());
            updatedChild.setFatherEmail(child.getFatherEmail() != null ? child.getFatherEmail() : existingChild.getFatherEmail());
            updatedChild.setFatherName(child.getFatherName() != null ? child.getFatherName() : existingChild.getFatherName());
            updatedChild.setFatherPhoneNumber(child.getFatherPhoneNumber() != null ? child.getFatherPhoneNumber() : existingChild.getFatherPhoneNumber());
            updatedChild.setMotherEmail(child.getMotherEmail() != null ? child.getMotherEmail() : existingChild.getMotherEmail());
            updatedChild.setMotherName(child.getMotherName() != null ? child.getMotherName() : existingChild.getMotherName());
            updatedChild.setMotherPhoneNumber(child.getMotherPhoneNumber() != null ? child.getMotherPhoneNumber() : existingChild.getMotherPhoneNumber());
            updatedChild.setGuardianEmail(child.getGuardianEmail() != null ? child.getGuardianEmail() : existingChild.getGuardianEmail());
            updatedChild.setGuardianName(child.getGuardianName() != null ? child.getGuardianName() : existingChild.getGuardianName());
            updatedChild.setGuardianPhoneNumber(child.getGuardianPhoneNumber() != null ? child.getGuardianPhoneNumber() : existingChild.getGuardianPhoneNumber());
            updatedChild.setDateOfBirth(child.getDateOfBirth() != null ? child.getDateOfBirth() : existingChild.getDateOfBirth());
            updatedChild.setNote(child.getNote() != null ? child.getNote() : existingChild.getNote());
            updatedChild.setUpdateDate(new Date());
            Integer version = existingChild.getVer();
            updatedChild.setVer(version++);
            return childRepository.save(updatedChild);
        }
        return null;
    }

    @Override
    public void deleteChild(Long id) {
        Child existingChild = childRepository.findById(id).orElse(null);
        if (existingChild != null) {
            childRepository.delete(existingChild);
        }
    }

    @Override
    public List<Child> findAllChildren() {
        return childRepository.findAll();
    }

    @Override
    public Child findOneChild(Long id) {
        return childRepository.findById(id).orElse(null);
    }
}
