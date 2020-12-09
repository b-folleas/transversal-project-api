package com.projettransversal.api.Models;


import javax.persistence.*;

@Entity
@Table(name = "Fire", schema = "public")
public class Fire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "map_item_id", referencedColumnName = "id")
    private MapItem mapItem;

    @Column(name = "intensity")
    private float intensity;

    public Fire(MapItem mapItem, float intensity) {
        this.mapItem = mapItem;
        this.intensity = intensity;
    }

    public Fire() {
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
        return mapItem;
    }

    public void setMapItem(MapItem mapItem) {
        this.mapItem = mapItem;
    }
}
