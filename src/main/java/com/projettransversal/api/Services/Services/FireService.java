package com.projettransversal.api.Services.Services;

import com.projettransversal.api.DAL.Interfaces.IFireRepository;
import com.projettransversal.api.DAL.Repositories.FireRepository;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Services.IServices.IFireService;
import com.projettransversal.api.Models.Fire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireService implements IFireService {
    private final FireRepository _fireRepository;

    public FireService(FireRepository fireRepository) {
        _fireRepository = fireRepository;
    }

    public List<Fire> findAll() {
        return (List<Fire>) _fireRepository.findAll();
    }

    public Optional<Fire> findById(int id) {
        return _fireRepository.findById(id);
    }

    public Fire save(Fire product) {
        return _fireRepository.save(product);
    }
}
