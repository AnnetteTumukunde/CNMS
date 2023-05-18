package rw.auca.cnms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import rw.auca.cnms.model.Serving;
import rw.auca.cnms.model.Users;

import java.util.List;

public interface IServingService {
    public Serving createServing(Serving serving, MultipartFile recipeFile);
    public Serving updateServing(Serving serving, Long id);
    public void deleteServing(Long id);
    public List<Serving> findAllServing();
    public Serving findOneServing(Long id);
    Page<Serving> getPaginatedServings(Pageable pageable);
}
