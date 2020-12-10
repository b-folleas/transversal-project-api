package com.projettransversal.api.Helpers;

import com.projettransversal.api.Models.*;
import com.projettransversal.api.Repositories.MapItemRepository;

import java.util.ArrayList;
import java.util.List;

public class DataTest {
    public List<Incident> incidents;
    public List<Barrack> barracks;
    public List<Truck> trucks;

    public DataTest(MapItemRepository mapItemRepository) {
        List<MapItem> mapItems = (List<MapItem>) mapItemRepository.findAll();

        incidents = new ArrayList<Incident>() {
            {
                add(new Incident(mapItems.get(0), 5, IncidentType.FIRE));
                add(new Incident(mapItems.get(1),  9, IncidentType.FIRE));
                add(new Incident(mapItems.get(2), 7, IncidentType.FIRE));
                add(new Incident(mapItems.get(3), 1, IncidentType.FIRE));
                add(new Incident(mapItems.get(4), 2, IncidentType.FIRE));
                add(new Incident(mapItems.get(5), 3, IncidentType.FIRE));
            }
        };

        barracks = new ArrayList<Barrack>() {
            {
                add(new Barrack(mapItems.get(6)));
                add(new Barrack(mapItems.get(7)));
                add(new Barrack(mapItems.get(8)));
                add(new Barrack(mapItems.get(9)));
                add(new Barrack(mapItems.get(10)));
                add(new Barrack(mapItems.get(11)));
            }
        };

        trucks = new ArrayList<Truck>() {
            {
                add(new Truck(mapItems.get(12), true, barracks.get(0)));
                add(new Truck(mapItems.get(13), true, barracks.get(1)));
                add(new Truck(mapItems.get(14), true, barracks.get(2)));
                add(new Truck(mapItems.get(15), true, barracks.get(3)));
                add(new Truck(mapItems.get(16), true, barracks.get(4)));
                add(new Truck(mapItems.get(17), true, barracks.get(5)));
            }
        };
    }
}
