package com.projettransversal.api.IServices;

import com.projettransversal.api.models.Fire;
import java.util.List;
import java.util.Optional;

public interface IFireService {
    public List<Fire> findAll();
    public Optional<Fire> findById(int id);
    public Fire save(Fire product);
}
