package com.projettransversal.api.Models;

public class Fire {
    private int id;
    private MapItem MapItem;
    private float intensity;

    public Fire(int id, com.projettransversal.api.Models.MapItem mapItem, float intensity) {
        this.id = id;
        MapItem = mapItem;
        this.intensity = intensity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public MapItem getMapItem() {
        return MapItem;
    }

    public void setMapItem(MapItem mapItem) {
        MapItem = mapItem;
    }
}
