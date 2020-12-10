package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.Incident;

import java.util.List;

public interface IIncidentService extends ICrudService<Incident> {
    List<Incident> findByData(Incident incident);
}
