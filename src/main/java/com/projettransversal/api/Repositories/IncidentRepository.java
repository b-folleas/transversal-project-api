package com.projettransversal.api.Repositories;

import com.projettransversal.api.Models.Incident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Integer> {

}
