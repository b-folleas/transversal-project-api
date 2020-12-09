package com.projettransversal.api.Helpers;

import com.projettransversal.api.Models.*;

import java.util.ArrayList;
import java.util.List;

public class DataTest {
    public List<MapItem> mapItems;
    public List<Incident> incidents;
    public List<Barrack> barracks;
    public List<Truck> trucks;

    public DataTest() {
        mapItems = new ArrayList<MapItem>() {
            {
                add(new MapItem(1,1, Ground.BUILDING));
                add(new MapItem(9,1, Ground.ROAD));
                add(new MapItem(5,4, Ground.GARDEN));
                add(new MapItem(6,1, Ground.BUILDING));
                add(new MapItem(9,7, Ground.ROAD));
                add(new MapItem(5,3, Ground.GARDEN));
            }
        };

        incidents = new ArrayList<Incident>() {
            {
                add(new Incident(mapItems.get(0), 5, IncidentType.FIRE));
                add(new Incident(mapItems.get(1),  9, IncidentType.FIRE));
                add(new Incident(mapItems.get(2), 7, IncidentType.FIRE));
                add(new Incident(mapItems.get(3), 1, IncidentType.FIRE));
                add(new Incident(mapItems.get(4), 2, IncidentType.FIRE));
                add(new Incident(mapItems.get(4), 3, IncidentType.FIRE));
            }
        };

        barracks = new ArrayList<Barrack>() {
            {
                add(new Barrack(mapItems.get(0)));
                add(new Barrack(mapItems.get(1)));
                add(new Barrack(mapItems.get(2)));
                add(new Barrack(mapItems.get(3)));
                add(new Barrack(mapItems.get(4)));
                add(new Barrack(mapItems.get(5)));
            }
        };

        trucks = new ArrayList<Truck>() {
            {
                add(new Truck(mapItems.get(0), true, barracks.get(0)));
                add(new Truck(mapItems.get(1), true, barracks.get(1)));
                add(new Truck(mapItems.get(2), true, barracks.get(2)));
                add(new Truck(mapItems.get(3), true, barracks.get(3)));
                add(new Truck(mapItems.get(4), true, barracks.get(4)));
                add(new Truck(mapItems.get(5), true, barracks.get(5)));
            }
        };
    }
}
