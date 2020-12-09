package com.projettransversal.api.Models;

import javax.persistence.*;

@Entity
@Table(name = "map_item", schema = "public")
public class MapItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "posX")
    private int posX;

    @Column(name = "posY")
    private int posY;

    @Column(name = "ground")
    @Enumerated(EnumType.STRING)
    private Ground ground;

    public MapItem() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
