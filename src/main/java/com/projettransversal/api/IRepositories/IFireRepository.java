package com.projettransversal.api.IRepositories;

import com.projettransversal.api.models.Fire;

import java.util.List;
import java.util.Optional;

public interface IFireRepository {
    public List<Fire> findAll();
    public Optional<Fire> findById(int id);
    public Fire save(Fire product);
}
