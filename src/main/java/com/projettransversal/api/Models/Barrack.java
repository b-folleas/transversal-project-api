package com.projettransversal.api.Models;

import javax.persistence.*;

@Entity
@Table(name = "barrack", schema = "public")
public class Barrack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "map_item_id", referencedColumnName = "id")
    private MapItem mapItem;

    public Barrack() {
    }

    public Barrack(MapItem mapItem) {
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
