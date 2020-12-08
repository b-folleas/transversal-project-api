package com.projettransversal.api.controllers;

import com.projettransversal.api.models.Fire;
import com.projettransversal.api.models.Ground;
import com.projettransversal.api.models.MapItem;
import com.projettransversal.api.models.Truck;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class TruckController {

    @RequestMapping(value="/trucks/{id}", method= RequestMethod.GET)
    public Optional<Truck> getTruckById(@PathVariable int id) {
        List<Truck> trucks =  new ArrayList<Truck>();
        trucks.add(new Truck(0, new MapItem(5,5, Ground.ROAD)));
        trucks.add(new Truck(1, new MapItem(5,5, Ground.ROAD)));
        trucks.add(new Truck(2, new MapItem(5,5, Ground.ROAD)));

        return trucks.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

}
