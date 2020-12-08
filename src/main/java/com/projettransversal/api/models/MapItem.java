package com.projettransversal.api.models;

public class MapItem {
    private int posX;
    private int posY;
    private Ground ground;

    public MapItem(int posX, int posY, Ground ground) {
        this.posX = posX;
        this.posY = posY;
        this.ground = ground;
    }

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

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }
}
