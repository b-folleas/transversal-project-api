package com.projettransversal.api.Models.ViewModels;

import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Services.IServices.IMapItemService;

import java.io.Serializable;
import java.util.Optional;

public class BarrackViewModel implements Serializable {

    private int posX;
    private int posY;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Barrack> toModel(IMapItemService iMapItemService) {
        MapItem mapItem = iMapItemService.findByCoordinates(this.posX, this.posY);
        return mapItem != null ? Optional.of(new Barrack(this.name, mapItem)) : Optional.empty();
    }
}
