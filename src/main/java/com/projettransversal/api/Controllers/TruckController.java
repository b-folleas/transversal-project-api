package com.projettransversal.api.Controllers;

import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Services.IServices.ITruckService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TruckController {

    private final ITruckService _truckService;

    public TruckController(ITruckService truckService) {
        _truckService = truckService;
    }

    @RequestMapping(value="/trucks", method= RequestMethod.GET)
    public List<Truck> getTrucks() {
        return _truckService.findAll();
    }

    @RequestMapping(value="/truck/{id}", method= RequestMethod.GET)
    public Optional<Truck> getTruckById(@PathVariable int id) {
        return _truckService.findById(id);
    }

}
