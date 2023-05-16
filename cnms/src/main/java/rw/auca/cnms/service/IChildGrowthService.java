package rw.auca.cnms.service;

import rw.auca.cnms.model.Child;
import rw.auca.cnms.model.ChildGrowth;

import java.util.List;

public interface IChildGrowthService {
    public ChildGrowth addChildGrowth(ChildGrowth childGrowth);
    public ChildGrowth updateChildGrowth(ChildGrowth childGrowth, Long id);
    public void deleteChildGrowth(Long id);
    public List<ChildGrowth> findAllChildGrowth();
    public ChildGrowth findOneChildGrowth(Long id);
}
