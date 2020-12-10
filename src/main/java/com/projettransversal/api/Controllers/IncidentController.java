package com.projettransversal.api.Controllers;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Services.IServices.IIncidentService;
import com.projettransversal.api.Uart.WriteUartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IncidentController {
    private final IIncidentService _incidentService;
    private final WriteUartService _writeUartService;

    public IncidentController(IIncidentService incidentService, WriteUartService writeUartService) {
        _incidentService = incidentService;
        _writeUartService = writeUartService;
    }

    @RequestMapping(value="/incidents", method= RequestMethod.GET)
    public List<Incident> getIncidents() {
        return _incidentService.findAll();
    }

    @RequestMapping(value="/incident/{id}", method= RequestMethod.GET)
    public Optional<Incident> getIncidentById(@PathVariable int id) {
        return _incidentService.findById(id);
    }

    @RequestMapping(value="/incident", method= RequestMethod.POST)
    public Incident getIncidentById(@RequestBody Incident incident) {
        _incidentService.insertOrUpdate(incident);
        _writeUartService.write(getIncidents());
        return incident;
    }
}
