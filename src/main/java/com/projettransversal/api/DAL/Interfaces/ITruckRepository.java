package com.projettransversal.api.DAL.Interfaces;

import com.projettransversal.api.Models.Fire;
import com.projettransversal.api.Models.Truck;

import java.util.List;
import java.util.Optional;

public interface ITruckRepository {
    public List<Truck> findAll();
    public Optional<Truck> findById(int id);
    public Truck save(Truck truck);
}
