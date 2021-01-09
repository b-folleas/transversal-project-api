package com.projettransversal.api.Services.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.MQTT.MQTTService;
import com.projettransversal.api.MQTT.ViewModels.IncidentViewModel;
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
import java.util.stream.Collectors;

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

    public Incident findByCompleteData(Incident incident) {
        List<Incident> incidents = List.copyOf(_incidentRepository
            .findByCompleteData(incident.getIntensity(), incident.getIncidentType().toString(), incident.getMapItem().getId())
        );
        if (incidents.size() != 0) {
            return incidents.get(0);
        }
        return null;
    }

    public Incident findByData(IncidentType type, MapItem mapItem) {
        List<Incident> incidents =  List.copyOf(_incidentRepository
            .findByData(type.toString(), mapItem.getId())
        );
        if (incidents.size() != 0) {
            return incidents.get(0);
        }
        return null;
    }

    public void addData(DataRequestDTO dataRequestDTO) throws JsonProcessingException {
        String data = dataRequestDTO.getData();
        List<Incident> incidents = this.mapMessageToIncidents(data);
        List<Incident> newIncidents = this.keepNewOrUpdatedIncidents(incidents);

        List<Incident> incidentsToDelete = newIncidents
                .stream().filter(i -> i.getIntensity() == 0)
                .collect(Collectors.toList());
        this.deleteMultiple(incidentsToDelete);

        List<Incident> incidentsToAddOrToUpdate = newIncidents
                .stream().filter(i -> i.getIntensity() != 0)
                .collect(Collectors.toList());
        this.insertOrUpdateMultiple(incidentsToAddOrToUpdate);

        this.logger.info(incidentsToAddOrToUpdate.size() + " incidents inserted");

        List<IncidentViewModel> incidentsToAddViewModel = new ArrayList<IncidentViewModel>();
        for (Incident incident : incidents) {
            IncidentViewModel incidentVM = new IncidentViewModel();
            incidentVM.setId(incident.getId());
            incidentVM.setIntensity(incident.getIntensity());
            incidentVM.setIntensityTag(incident.getIntensity());
            incidentVM.setPosX(incident.getMapItem().getPosX());
            incidentVM.setPosY(incident.getMapItem().getPosY());
            incidentVM.setGround(incident.getMapItem().getGround());
            incidentVM.setIncidentType(incident.getIncidentType());

            incidentsToAddViewModel.add(incidentVM);
        }

        _mqttService.sendToBroker(new ObjectMapper().writeValueAsString(incidentsToAddViewModel));
        logger.info(incidents.size() + " incidents send by MQTT");
    }

    private List<Incident> keepNewOrUpdatedIncidents(List<Incident> incidents) {
        List<Incident> incidentsToAdd = new ArrayList<Incident>();

        for (Incident incident : incidents) {
            Incident similarIncident = this.findByCompleteData(incident);
            if (similarIncident == null) {
                incidentsToAdd.add(incident);
            } else {
                logger.info("Incident already present ! Not inserted");
            }
        }

        return incidentsToAdd;
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

    private Incident buildIncident(String type, List<Integer> positions){
        MapItem mapItem = _mapItemService.findByCoordinates(positions.get(0), positions.get(1));
        if (mapItem == null) {
            logger.info("No mapItem found for these coordinates. Return");
            return null;
        }

        IncidentType incidentType = IncidentType.FIRE;
        switch(type) {
            case "F":
                incidentType = IncidentType.FIRE;
                break;
            case "I":
                incidentType = IncidentType.FLOOD;
                break;
            case "A":
                incidentType = IncidentType.ACCIDENT;
                break;
            case "T":
                incidentType = IncidentType.TORNADO;
                break;
        }

        Incident incident = this.findByData(incidentType, mapItem);
        if (incident == null) {
            incident = new Incident();
        }

        incident.setMapItem(mapItem);
        incident.setIntensity(positions.get(2));
        incident.setIncidentType(incidentType);

        return incident;
    }
}
