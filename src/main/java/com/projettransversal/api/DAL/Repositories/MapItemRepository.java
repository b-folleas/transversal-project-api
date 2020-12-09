package com.projettransversal.api.DAL.Repositories;

import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapItemRepository extends CrudRepository<MapItem, Integer> {

}
