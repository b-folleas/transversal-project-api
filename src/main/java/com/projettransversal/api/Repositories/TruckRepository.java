package com.projettransversal.api.Repositories;

import com.projettransversal.api.Models.Truck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Integer> {

}
