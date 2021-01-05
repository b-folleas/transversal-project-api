package com.projettransversal.api.Services.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.MQTT.MQTTService;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentService extends CrudService<Incident> implements IIncidentService {

    private final Logger logger = LoggerFactory.getLogger(IncidentService.class);


    private final IncidentRepository _incidentRepository;
    private final MapItemService _mapItemService;
    private final MQTTService _mqttService;

    public IncidentService(IncidentRepository incidentRepository, MapItemService mapItemService, MQTTService mqttService) {
        super(incidentRepository);
        _incidentRepository = incidentRepository;
        _mapItemService = mapItemService;
        _mqttService = mqttService;
    }

    public List<Incident> findByData(Incident incident) {
        return List.copyOf(_incidentRepository
            .findByData(incident.getIntensity(), incident.getIncidentType().toString(), incident.getMapItem().getId()));
    }

    public void addData(DataRequestDTO dataRequestDTO) throws JsonProcessingException {
        String data = dataRequestDTO.getData();
        List<Incident> incidents = this.mapMessageToIncidents(data);
        List<Incident> incidentsToAdd = this.keepNewIncidents(incidents);

        this.insertOrUpdateMultiple(incidentsToAdd);
        this.logger.info(incidentsToAdd.size() + " incidents inserted");

        _mqttService.sendToBroker(new ObjectMapper().writeValueAsString(incidentsToAdd));
        logger.info(incidentsToAdd.size() + " incidents send by MQTT");
    }

    private List<Incident> mapMessageToIncidents(String data) {
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

    private List<Incident> keepNewIncidents(List<Incident> incidents) {
        List<Incident> incidentsToAdd = new ArrayList<Incident>();

        for (Incident incident : incidents) {
            List<Incident> similarIncidents = this.findByData(incident);
            if (similarIncidents.size() == 0) {
                incidentsToAdd.add(incident);
            } else {
                logger.info("Incident already present ! Not inserted");
            }
        }

        return incidentsToAdd;
    }

    private Incident buildIncident(String type, List<Integer> positions){
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
