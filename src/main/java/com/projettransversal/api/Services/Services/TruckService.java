package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Repositories.TruckRepository;
import com.projettransversal.api.Services.IServices.ITruckService;
import org.springframework.stereotype.Service;

@Service
public class TruckService extends CrudService<Truck> implements ITruckService {
    public TruckService(TruckRepository truckRepository) {
        super(truckRepository);
    }
}
