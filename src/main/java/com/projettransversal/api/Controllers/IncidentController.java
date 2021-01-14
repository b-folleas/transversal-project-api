package com.projettransversal.api.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.Models.Incident;
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
    @ApiOperation(value = "Récupération de tous les incidents")
    public List<Incident> getIncidents() {
        return _incidentService.findAll();
    }

    @RequestMapping(value = "/incident/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération d'un incident par son ID")
    public Optional<Incident> getIncidentById(@PathVariable Long id) {
        return _incidentService.findById(id);
    }

    @RequestMapping(value = "/incident/data", method = RequestMethod.POST)
    @ApiOperation(value = "Récupération des données envoyé depuis le microbit. Le format des données est le suivant : LettreTypeIncident/posX,posY,intensité&")
    public void postData(@RequestBody DataRequestDTO dataRequestDTO) throws JsonProcessingException {
        this._incidentService.addData(dataRequestDTO);
    }
}
