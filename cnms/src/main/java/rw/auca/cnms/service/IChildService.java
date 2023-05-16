package rw.auca.cnms.service;

import rw.auca.cnms.model.Child;

import java.util.List;

public interface IChildService {
    public Child registerChild(Child child);
    public Child updateChild(Child child, Long id);
    public void deleteChild(Long id);
    public List<Child> findAllChildren();
    public Child findOneChild(Long id);
}
