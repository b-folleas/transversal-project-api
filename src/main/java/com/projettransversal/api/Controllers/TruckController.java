package com.projettransversal.api.Controllers;

import com.projettransversal.api.Exception.IncidentNotFoundException;
import com.projettransversal.api.Exception.TruckNotFoundException;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Services.IServices.ITruckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TruckController {

    private final ITruckService _truckService;

    public TruckController(ITruckService truckService) {
        _truckService = truckService;
    }

    @GetMapping(value = "/trucks")
    public List<Truck> getTrucks() {
        return _truckService.findAll();
    }

    @GetMapping(value = "/truck/{id}")
    public Optional<Truck> getTruckById(@PathVariable int id) {
        return _truckService.findById(id);
    }

    @GetMapping(value = "/truck/{truck_id}/link/incident/{incident_id}")
    public ResponseEntity<Truck> linkIncidentToTruck(@PathVariable int truck_id, @PathVariable int incident_id) throws TruckNotFoundException, IncidentNotFoundException {
        return ResponseEntity.ok().body(_truckService.linkIncidentToTruck(truck_id, incident_id));
    }

}
