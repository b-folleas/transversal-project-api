package com.projettransversal.api.Services.IServices;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    T insertOrUpdate(T obj);
    void delete(T obj);
}
