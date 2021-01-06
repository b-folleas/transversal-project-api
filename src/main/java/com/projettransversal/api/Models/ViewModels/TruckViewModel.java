package com.projettransversal.api.Models.ViewModels;

import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Services.IServices.IMapItemService;

import java.io.Serializable;
import java.util.Optional;

public class TruckViewModel implements Serializable {

    private int posX;
    private int posY;
    private int matricule;
    private boolean availability;
    private Barrack barrack;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Barrack getBarrack() {
        return barrack;
    }

    public void setBarrack(Barrack barrack) {
        this.barrack = barrack;
    }

    public Optional<Truck> toModel(IMapItemService iMapItemService) {
        MapItem mapItem = iMapItemService.findByCoordinates(this.posX, this.posY);
        return mapItem != null ? Optional.of(new Truck(mapItem, this.availability, this.barrack, this.matricule)) : Optional.empty();
    }
}
