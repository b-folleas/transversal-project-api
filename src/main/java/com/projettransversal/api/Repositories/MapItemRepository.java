package com.projettransversal.api.Repositories;

import com.projettransversal.api.Models.MapItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapItemRepository extends CrudRepository<MapItem, Long> {

    @Query(value = "select * from map_item where posx = ?1 and posy = ?2", nativeQuery = true)
    MapItem findByCoordinates(int x, int y);
}
