package com.projettransversal.api.DAL.Repositories;

import com.projettransversal.api.Models.Fire;
import com.projettransversal.api.Models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireRepository extends JpaRepository<Fire, Integer> {

}
