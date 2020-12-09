package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import org.springframework.stereotype.Service;

@Service
public class IncidentService extends CrudService<Incident> implements IIncidentService {
    public IncidentService(IncidentRepository incidentRepository) {
        super(incidentRepository);
    }
}
