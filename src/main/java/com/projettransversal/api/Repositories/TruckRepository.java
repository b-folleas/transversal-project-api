package com.projettransversal.api.Repositories;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.Truck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Long> {

    @Query("SELECT t FROM Truck t WHERE :incident member of t.incidents")
    List<Truck> findAllByIncident(@Param("incident") Incident incident);
}
