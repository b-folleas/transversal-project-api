package com.projettransversal.api.Models.ViewModels;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Services.IServices.IMapItemService;

public class IncidentViewModel {

    private final int posX;
    private final int posY;
    private final float intensity;
    private final IncidentType incidentType;

    public IncidentViewModel(int id, int posX, int posY, float intensity, IncidentType incidentType) {
        this.posX = posX;
        this.posY = posY;
        this.intensity = intensity;
        this.incidentType = incidentType;
    }

    public Incident toModel(IMapItemService mapItemService) {
        MapItem mapItem = mapItemService.findByCoordinates(posX, posY);
        if (mapItem != null) {
            return new Incident(mapItem, intensity, incidentType);
        }
        return null;
    }

    public static IncidentViewModel fromModel(Incident i) {
        return new IncidentViewModel(i.getId(), i.getMapItem().getPosX(),
                i.getMapItem().getPosY(), i.getIntensity(), i.getIncidentType());
    }
}

