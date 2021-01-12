package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.ViewModels.IncidentViewModel;

import java.util.List;

public interface IIncidentService extends ICrudService<Incident> {
    List<Incident> findByData(Incident incident);

    Incident create(IncidentViewModel incidentVM) throws InterruptedException;

    Incident updateIncidentIntensity(Long incident_id, int new_intensity) throws InterruptedException;

}
