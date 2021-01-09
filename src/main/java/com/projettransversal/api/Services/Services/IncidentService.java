package com.projettransversal.api.Services.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.MQTT.MQTTService;
import com.projettransversal.api.MQTT.ViewModels.IncidentViewModel;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Repositories.TruckRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import com.projettransversal.api.Services.IServices.IMapItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentService extends CrudService<Incident> implements IIncidentService {

    private final Logger logger = LoggerFactory.getLogger(IncidentService.class);
    private final IncidentRepository _incidentRepository;
    private final IMapItemService _mapItemService;
    private final MQTTService _mqttService;
    private final TruckRepository truckRepository;

    public IncidentService(IncidentRepository incidentRepository, IMapItemService mapItemService, MQTTService mqttService, TruckRepository truckRepository) {
        super(incidentRepository);
        _incidentRepository = incidentRepository;
        _mapItemService = mapItemService;
        _mqttService = mqttService;
        this.truckRepository = truckRepository;
    }

    private List<Incident> getIncidentsFromDataRequest(DataRequestDTO dataRequestDTO) {
        // Création d'une liste d'incident vide.
        List<Incident> incidents = new ArrayList<Incident>();

        // Split en plusieurs string en fonction des différents type d'incidents.
        String[] incidentsTypes = dataRequestDTO.getData().split("&");

        // Pour chaque type d'incident on récupère les incidents existants, ou on les créé.
        for (String incidentsType : incidentsTypes) {
            String[] incidentsList = incidentsType.split("/");

            for (int i = 1; i < incidentsList.length; i++) {
                // Va récupérer en base de donnée l'incident correspondant, ou bien en créer un nouveau.
                incidents.add(buildIncident(incidentsList[0], incidentsList[i]));
            }
        }

        return incidents;
    }

    private Incident buildIncident(String type, String stringIncident) {

        String[] stringIncidentSplited = stringIncident.split(",");

        int posX = Integer.parseInt(stringIncidentSplited[0]);
        int posY = Integer.parseInt(stringIncidentSplited[1]);
        float intensity = Float.parseFloat(stringIncidentSplited[2]);

        IncidentType incidentType = IncidentType.fromString(type);

        // Récupération de l'incident en base.
        // Si jamais il n'existe pas alors il est créé avec le bon mapItem et incidentType.

        Incident incident = this._incidentRepository.findFromString(incidentType, posX, posY).orElse(new Incident(this._mapItemService.findByCoordinates(posX, posY), incidentType));

        incident.setIntensity(intensity);
        return incident;
    }

    public void addData(DataRequestDTO dataRequestDTO) throws JsonProcessingException {s
        logger.info(String.format("Récupération de la chaîne suivante : %s", dataRequestDTO.getData()));
        List<Incident> incidents = this.getIncidentsFromDataRequest(dataRequestDTO);
        logger.info(String.format("Récupération de %d élément(s).", incidents.size()));
        incidents.forEach(i -> logger.info(i.toString()));
        for (Incident incident : incidents) {
            // Si jamais l'intensité est à 0 il faut supprimer de la base de donnée l'intensité.
            if (incident.getIntensity() == 0) {

                // Il faut d'abord supprimer enlever l'association des trucks à l'incident et ensuite le supprimer.
                List<Truck> trucks = this.truckRepository.findAllByIncident(incident);

                for (Truck truck : trucks) {
                    truck.getIncidents().remove(incident);
                    truck.setAvailability(truck.getIncidents().size() == 0);
                    this.truckRepository.save(truck);
                }

                this.delete(incident);
            } else {
                Incident newIncident = this.insertOrUpdate(incident);

                // On bind le nouvel ID de l'incident sur l'objet pour qu'il soit accessible par le MQTT.
                if (incident.getId() == null) {
                    incident.setId(newIncident.getId());
                }
            }
        }

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
}
