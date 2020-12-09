package com.projettransversal.api.Models;

import javax.persistence.*;

@Entity
@Table(name = "Truck", schema = "public")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "map_item_id", referencedColumnName = "id")
    private MapItem mapItem;

    public Truck() {
    }

    public Truck(MapItem mapItem) {
        this.mapItem = mapItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MapItem getMapItem() {
        return this.mapItem;
    }

    public void setMapItem(MapItem mapItem) {
        this.mapItem = mapItem;
    }
}
