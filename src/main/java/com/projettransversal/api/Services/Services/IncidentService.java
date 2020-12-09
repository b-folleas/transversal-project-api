package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Services.IServices.IIncidentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService implements IIncidentService {
    private final IncidentRepository _incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        _incidentRepository = incidentRepository;
    }

    public List<Incident> findAll() {
        return (List<Incident>) _incidentRepository.findAll();
    }

    public Optional<Incident> findById(int id) {
        return _incidentRepository.findById(id);
    }
}
