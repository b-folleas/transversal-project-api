package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.Fire;
import java.util.List;
import java.util.Optional;

public interface IFireService {
    public List<Fire> findAll();
    public Optional<Fire> findById(int id);
    public Fire save(Fire product);
}
