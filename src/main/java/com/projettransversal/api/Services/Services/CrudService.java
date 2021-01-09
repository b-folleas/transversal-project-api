package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Services.IServices.ICrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<T> implements ICrudService<T> {
    private final CrudRepository<T, Long> _repository;

    public CrudService(CrudRepository<T, Long> repository) {
        _repository = repository;
    }

    public List<T> findAll() {
        return (List<T>) _repository.findAll();
    }

    public Optional<T> findById(Long id) {
        return _repository.findById(id);
    }

    public T insertOrUpdate(T obj) {
        return _repository.save(obj);
    }

    public void delete(T obj) {
        _repository.delete(obj);
    }
}
