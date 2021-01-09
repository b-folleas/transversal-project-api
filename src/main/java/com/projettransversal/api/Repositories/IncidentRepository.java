package com.projettransversal.api.Repositories;

import com.projettransversal.api.Models.Incident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long> {

    @Query(
            value = "select * from incident where intensity = ?1 and incident_type = ?2 and map_item_id = ?3",
            nativeQuery = true)
    Collection<Incident> findByData(float intensity, String y, Long mapItemId);
}
