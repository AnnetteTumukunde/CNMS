package rw.auca.cnms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.model.ChildGrowth;
import rw.auca.cnms.model.Users;

import java.util.List;

public interface IChildGrowthService {
    public ChildGrowth addChildGrowth(ChildGrowth childGrowth);
    public ChildGrowth updateChildGrowth(ChildGrowth childGrowth, Long id);
    public void deleteChildGrowth(Long id);
    public List<ChildGrowth> findAllChildGrowth();
    public ChildGrowth findOneChildGrowth(Long id);
    Page<ChildGrowth> getPaginatedChildGrowths(Pageable pageable);
}
