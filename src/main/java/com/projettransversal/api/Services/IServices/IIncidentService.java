package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.Incident;
import java.util.List;
import java.util.Optional;

public interface IIncidentService {
    public List<Incident> findAll();
    public Optional<Incident> findById(int id);
}
