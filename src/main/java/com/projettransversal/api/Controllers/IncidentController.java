package com.projettransversal.api.Controllers;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.ViewModels.IncidentViewModel;
import com.projettransversal.api.Services.IServices.IIncidentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IncidentController {
    private final IIncidentService _incidentService;

    public IncidentController(IIncidentService incidentService) {
        _incidentService = incidentService;
    }

    @RequestMapping(value = "/incidents", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération de tous les incidents.")
    public List<Incident> getIncidents() {
        return _incidentService.findAll();
    }

    @RequestMapping(value = "/incident/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération d'un incident en fonction de son ID.")
    public Optional<Incident> getIncidentById(@PathVariable Long id) {
        return _incidentService.findById(id);
    }

    @RequestMapping(value = "/incident", method = RequestMethod.POST)
    @ApiOperation(value = "Création d'un incident à travers l'objet IncidentViewModel.")
    public Incident getIncidentById(@RequestBody IncidentViewModel incidentVM) {
        return _incidentService.create(incidentVM);
    }

    @RequestMapping(value = "/incident/{incident_id}/intensity/{new_intensity}", method = RequestMethod.POST)
    @ApiOperation(value = "Modification de l'intensité d'un incident à travers son ID.")
    public Incident updateIncident(@PathVariable Long incident_id, @PathVariable int new_intensity) {
        return this._incidentService.updateIncidentIntensity(incident_id, new_intensity);
    }

}
