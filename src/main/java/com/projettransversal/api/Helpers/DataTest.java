package com.projettransversal.api.Helpers;

import com.projettransversal.api.Models.Fire;
import com.projettransversal.api.Models.Ground;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Models.Truck;

import java.util.ArrayList;
import java.util.List;

public class DataTest {
    public List<MapItem> mapItems;
    public List<Fire> fires;
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

        fires = new ArrayList<Fire>() {
            {
                add(new Fire(mapItems.get(0), 5));
                add(new Fire(mapItems.get(1),  9));
                add(new Fire(mapItems.get(2), 7));
                add(new Fire(mapItems.get(3), 1));
                add(new Fire(mapItems.get(4), 2));
                add(new Fire(mapItems.get(4), 3));
            }
        };

        trucks = new ArrayList<Truck>() {
            {
                add(new Truck(mapItems.get(0)));
                add(new Truck(mapItems.get(1)));
                add(new Truck(mapItems.get(2)));
                add(new Truck(mapItems.get(3)));
                add(new Truck(mapItems.get(4)));
                add(new Truck(mapItems.get(5)));
            }
        };
    }
}
