package com.projettransversal.api.MQTT.ViewModels;

import com.projettransversal.api.Models.Ground;
import com.projettransversal.api.Models.IncidentType;

public class IncidentViewModel {
    public float intensity;
    public int posX;
    public int poxY;
    public int ground;
    public int incidentType;

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPoxY() {
        return poxY;
    }

    public void setPoxY(int poxY) {
        this.poxY = poxY;
    }

    public int getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground.ordinal();
    }

    public int getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType.ordinal();
    }
}


