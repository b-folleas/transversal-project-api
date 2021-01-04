package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Exception.IncidentNotFoundException;
import com.projettransversal.api.Exception.TruckNotFoundException;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.ProjetTransversalExceptionEnum;
import com.projettransversal.api.Repositories.TruckRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import com.projettransversal.api.Services.IServices.ITruckService;
import org.springframework.stereotype.Service;

@Service
public class TruckService extends CrudService<Truck> implements ITruckService {

    private final IIncidentService incidentService;

    public TruckService(TruckRepository truckRepository, IIncidentService incidentService) {
        super(truckRepository);
        this.incidentService = incidentService;
    }

    public Truck linkIncidentToTruck(int truck_id, int incident_id) throws TruckNotFoundException, IncidentNotFoundException {
        Truck truck = this.findById(truck_id).orElseThrow(() -> new TruckNotFoundException(ProjetTransversalExceptionEnum.TRUCK_NOT_FOUND, truck_id));
        Incident incident = this.incidentService.findById(incident_id).orElseThrow(() -> new IncidentNotFoundException(ProjetTransversalExceptionEnum.INCIDENT_NOT_FOUND, incident_id));

        truck.getIncidents().add(incident);
        truck.setAvailability(false);
        return this.insertOrUpdate(truck);
    }
}
