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

    @OneToOne()
    @JoinColumn(name = "barrack_id", referencedColumnName = "id")
    private Barrack barrack;

    @Column(name = "availability")
    private boolean availability;

    public Truck() {
    }

    public Truck(MapItem mapItem, boolean availability, Barrack barrack) {
        this.mapItem = mapItem;
        this.availability = availability;
        this.barrack = barrack;
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
