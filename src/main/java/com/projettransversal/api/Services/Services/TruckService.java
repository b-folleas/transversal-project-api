package com.projettransversal.api.Services.Services;

import com.projettransversal.api.DAL.Interfaces.ITruckRepository;
import com.projettransversal.api.DAL.Repositories.TruckRepository;
import com.projettransversal.api.Models.Fire;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Services.IServices.ITruckService;
import com.projettransversal.api.Services.IServices.ITruckService;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckService implements ITruckService {
    private final TruckRepository _truckRepository;

    public TruckService(TruckRepository truckRepository) {
        _truckRepository = truckRepository;
    }

    public List<Truck> findAll() {
        return (List<Truck>) _truckRepository.findAll();
    }

    public Optional<Truck> findById(int id) {
        Optional<Truck> a = _truckRepository.findById(id);
        return a;
    }
}
