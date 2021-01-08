package com.projettransversal.api.Services.IServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.MapItem;

public interface IIncidentService extends ICrudService<Incident> {
    Incident findByData(IncidentType type, MapItem mapItem);

    void addData(DataRequestDTO data) throws JsonProcessingException;
}
