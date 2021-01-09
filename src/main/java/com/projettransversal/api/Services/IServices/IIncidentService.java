package com.projettransversal.api.Services.IServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.Models.Incident;

public interface IIncidentService extends ICrudService<Incident> {
    void addData(DataRequestDTO data) throws JsonProcessingException;
}
