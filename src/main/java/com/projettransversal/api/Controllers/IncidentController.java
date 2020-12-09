package com.projettransversal.api.Controllers;

import com.projettransversal.api.Services.IServices.IIncidentService;
import com.projettransversal.api.Models.Incident;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
