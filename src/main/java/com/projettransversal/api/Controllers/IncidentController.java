package com.projettransversal.api.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Services.IServices.IIncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IncidentController {
    private final IIncidentService _incidentService;

    public IncidentController(IIncidentService incidentService) {
        _incidentService = incidentService;
    }

    @RequestMapping(value="/incidents", method= RequestMethod.GET)
    public List<Incident> getIncidents() {
        return _incidentService.findAll();
    }

    @RequestMapping(value="/incident/{id}", method= RequestMethod.GET)
    public Optional<Incident> getIncidentById(@PathVariable int id) {
        return _incidentService.findById(id);
    }

    @RequestMapping(value="/incident/data", method=RequestMethod.POST)
    public void postData(@RequestBody DataRequestDTO dataRequestDTO) throws JsonProcessingException {
        this._incidentService.addData(dataRequestDTO);
    }
}
