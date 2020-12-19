package com.projettransversal.api.Uart;

import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Services.Services.IncidentService;
import com.projettransversal.api.Services.Services.MapItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UartHelper {

    private final Logger logger = LoggerFactory.getLogger(UartHelper.class);

    private final IncidentService _incidentService;
    private final MapItemService _mapItemService;

    public UartHelper(MapItemService mapItemService, IncidentService incidentService) {
        _mapItemService = mapItemService;
        _incidentService = incidentService;
    }

    public String sanitizeMessage(String data) {
        return data
            .replaceAll(" ", "")
            .replaceAll("\"", "")
            .replaceAll("'", "")
            .replaceAll("(?:\\n|\\r)", "");
    }

    public List<Incident> mapMessageToIncidents(String data) {
        List<Incident> incidents = new ArrayList<Incident>();

        String[] incidentsTypes = data.toString().split("&");
        for (String incidentsType : incidentsTypes) {
            String[] incidentsList = incidentsType.split("/");

            for (int i = 1; i < incidentsList.length ; i++ ) {
                List<Integer> positionsList = new ArrayList<Integer>();

                for (String position : incidentsList[i].split(",")) {
                    positionsList.add(Integer.parseInt(position));
                }

                incidents.add(buildIncident(incidentsList[0], positionsList));
            }
        }

        return incidents;
    }

    public List<Incident> keepNewIncidents(List<Incident> incidents) {
        List<Incident> incidentsToAdd = new ArrayList<Incident>();

        for (Incident incident : incidents) {
            List<Incident> similarIncidents = _incidentService.findByData(incident);
            if (similarIncidents.size() == 0) {
                incidentsToAdd.add(incident);
            } else {
                logger.info("Incident already present ! Not inserted");
            }
        }

        return incidentsToAdd;
    }

    public Incident buildIncident(String type, List<Integer> positions){
        MapItem mapItem = _mapItemService.findByCoordinates(positions.get(0), positions.get(1));
        if (mapItem == null) {
            logger.info("No mapItem found for these coordinates. Return");
            return null;
        }

        Incident incident = new Incident();
        incident.setMapItem(mapItem);
        incident.setIntensity(positions.get(2));

        switch(type) {
            case "F":
                incident.setIncidentType(IncidentType.FIRE);
                break;
            case "I":
                incident.setIncidentType(IncidentType.FLOOD);
                break;
            case "A":
                incident.setIncidentType(IncidentType.ACCIDENT);
                break;
            case "T":
                incident.setIncidentType(IncidentType.TORNADO);
                break;
        }

        return incident;
    }
}
