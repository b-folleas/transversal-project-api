package com.projettransversal.api.Controllers;

import com.projettransversal.api.Exception.IncidentNotFoundException;
import com.projettransversal.api.Exception.MapItemNotFoundException;
import com.projettransversal.api.Exception.TruckNotFoundException;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Models.ViewModels.TruckViewModel;
import com.projettransversal.api.Services.IServices.ITruckService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TruckController {

    private final ITruckService _truckService;

    public TruckController(ITruckService truckService) {
        _truckService = truckService;
    }

    @GetMapping(value = "/trucks")
    @ApiOperation(value = "Récupération de tous les trucks")
    public List<Truck> getTrucks() {
        return _truckService.findAll();
    }

    @GetMapping(value = "/truck/{id}")
    @ApiOperation(value = "Récupération d'un truck par son ID")
    public Optional<Truck> getTruckById(@PathVariable Long id) {
        return _truckService.findById(id);
    }

    @GetMapping(value = "/truck/{truck_id}/link/incident/{incident_id}")
    @ApiOperation(value = "Lie un incident à un truck")
    public ResponseEntity<Truck> linkIncidentToTruck(@PathVariable Long truck_id, @PathVariable Long incident_id) throws TruckNotFoundException, IncidentNotFoundException {
        return ResponseEntity.ok().body(_truckService.linkIncidentToTruck(truck_id, incident_id));
    }

    @RequestMapping(value = "/truck/{truck_id}/posx/{posx}/posy/{posy}", method = RequestMethod.POST)
    @ApiOperation(value = "Déplace un truck sur une mapItem")
    public Truck moveTruck(@PathVariable Long truck_id, @PathVariable int posx, @PathVariable int posy) throws MapItemNotFoundException, TruckNotFoundException {
        return this._truckService.moveTruck(truck_id, posx, posy);
    }

    @RequestMapping(value = "/truck", method = RequestMethod.POST)
    @ApiOperation(value = "Création d'un truck à travers l'objet TruckViewModel")
    public Truck createTruck(@RequestBody TruckViewModel truckVM) throws MapItemNotFoundException {
        return this._truckService.create(truckVM);
    }

}
