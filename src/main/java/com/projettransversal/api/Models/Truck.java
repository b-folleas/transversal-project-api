package com.projettransversal.api.Models;

public class Truck {
    private int id;
    private MapItem MapItem;

    public Truck(int id, com.projettransversal.api.Models.MapItem mapItem) {
        this.id = id;
        MapItem = mapItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.projettransversal.api.Models.MapItem getMapItem() {
        return MapItem;
    }

    public void setMapItem(com.projettransversal.api.Models.MapItem mapItem) {
        MapItem = mapItem;
    }
}
