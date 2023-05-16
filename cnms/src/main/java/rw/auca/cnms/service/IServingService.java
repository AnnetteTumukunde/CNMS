package rw.auca.cnms.service;

import rw.auca.cnms.model.Serving;

import java.util.List;

public interface IServingService {
    public Serving createServing(Serving serving);
    public Serving updateServing(Serving serving, Long id);
    public void deleteServing(Long id);
    public List<Serving> findAllServing();
    public Serving findOneServing(Long id);
}
