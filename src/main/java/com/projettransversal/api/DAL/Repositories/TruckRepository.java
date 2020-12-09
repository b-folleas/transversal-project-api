package com.projettransversal.api.DAL.Repositories;

import com.projettransversal.api.Models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {

}
