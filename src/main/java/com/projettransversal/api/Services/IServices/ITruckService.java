package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.Truck;

import java.util.List;
import java.util.Optional;

public interface ITruckService {
    List<Truck> findAll();
    Optional<Truck> findById(int id);
}
