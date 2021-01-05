package com.projettransversal.api.Services.IServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.Models.Incident;

import java.util.List;

public interface IIncidentService extends ICrudService<Incident> {
    List<Incident> findByData(Incident incident);

    void addData(DataRequestDTO data) throws JsonProcessingException;
}
