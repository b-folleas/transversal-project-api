package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.ViewModels.IncidentViewModel;


public interface IIncidentService extends ICrudService<Incident> {

    Incident create(IncidentViewModel incidentVM);

    Incident updateIncidentIntensity(Long incident_id, int new_intensity);

}
