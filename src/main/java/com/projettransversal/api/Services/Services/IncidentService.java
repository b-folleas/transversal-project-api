package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService extends CrudService<Incident> implements IIncidentService {
    private final IncidentRepository _incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        super(incidentRepository);
        _incidentRepository = incidentRepository;
    }

    public List<Incident> findByData(Incident incident) {
        return List.copyOf(_incidentRepository
            .findByData(incident.getIntensity(), incident.getIncidentType().toString(), incident.getMapItem().getId()));
    }

    public Incident updateIncidentIntensity(int incident_id, int new_intensity){
         Incident incident = _incidentRepository.findById(incident_id).get();
         incident.setIntensity(new_intensity);
         return this.insertOrUpdate(incident);
    }
}
