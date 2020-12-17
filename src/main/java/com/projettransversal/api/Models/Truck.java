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

    @Column(name = "matricule")
    private int matricule;

    public Truck() {
    }

    public Truck(MapItem mapItem, boolean availability, Barrack barrack, int matricule) {
        this.mapItem = mapItem;
        this.barrack = barrack;
        this.availability = availability;
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MapItem getMapItem() {
        return mapItem;
    }

    public void setMapItem(MapItem mapItem) {
        this.mapItem = mapItem;
    }

    public Barrack getBarrack() {
        return barrack;
    }

    public void setBarrack(Barrack barrack) {
        this.barrack = barrack;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
}
