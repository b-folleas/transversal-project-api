package com.projettransversal.api.Services.Services;

import com.projettransversal.api.DTO.MicrobitRequestDTO;
import com.projettransversal.api.DTO.MicrobitResponseDTO;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.ViewModels.IncidentViewModel;
import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import com.projettransversal.api.Services.IServices.IMapItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncidentService extends CrudService<Incident> implements IIncidentService {

    private final Logger logger = LoggerFactory.getLogger(IncidentService.class);
    private final IncidentRepository _incidentRepository;
    private final IMapItemService _mapItemService;
    @Value("${microbit.url}")
    String microbitUrl;
    @Value("${microbit.path}")
    String microbitPath;
    private WebClient webClient;

    public IncidentService(IncidentRepository incidentRepository, IMapItemService mapItemService) {
        super(incidentRepository);
        _incidentRepository = incidentRepository;
        _mapItemService = mapItemService;
    }

    public Incident updateIncidentIntensity(Long incident_id, int new_intensity) {
        Optional<Incident> incidentOptional = _incidentRepository.findById(incident_id);

        if(incidentOptional.isEmpty()) {
            return null;
        }

        Incident incident = incidentOptional.get();
        incident.setIntensity(new_intensity);

        return this.updateOrDeleteAndSave(incident);
    }

    private Incident updateOrDeleteAndSave(Incident incident) {
        if (incident.getIntensity() != 0) {
            incident = this.insertOrUpdate(incident);
        }

        List<Incident> incidentsToSend = this.findAll();

        if (incident.getIntensity() == 0) {
            this.delete(incident);
            final Long incidentId = incident.getId();

            Optional<Incident> incidentOptional = incidentsToSend.stream().filter(i -> i.getId().equals(incidentId)).findFirst();

            if (incidentOptional.isPresent()) {
                incidentOptional.get().setIntensity(0);
            } else {
                incidentsToSend.add(incident);
            }
        }

        this.sendToMicrobit(incidentsToSend);
        return incident;
    }

    public Incident create(IncidentViewModel incidentVM) {
        Incident incident = incidentVM.toModel(this._mapItemService);
        return this.updateOrDeleteAndSave(incident);
    }

    private void sendToMicrobit(List<Incident> incidents) {
        String formattedData = formatData(incidents);
        logger.info(String.format("Envoi de '%s' à l'API", formattedData));
        MicrobitResponseDTO microbitResponseDTO = this.sendData(new MicrobitRequestDTO(formattedData));
        if (!microbitResponseDTO.getCode()) {
            logger.error("Erreur lors de l'envoi au microbit");
        }
    }

    private MicrobitResponseDTO sendData(MicrobitRequestDTO microbitRequest) {
        return this.getWebClient()
                .post()
                .uri(this.microbitPath)
                .body(Mono.just(microbitRequest), MicrobitRequestDTO.class)
                .retrieve()
                .bodyToMono(MicrobitResponseDTO.class)
                .block();
    }

    private String formatData(List<Incident> incidents) {
        StringBuilder returnString = new StringBuilder();

        List<Incident> fires = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.FIRE)
                .collect(Collectors.toList());

        List<Incident> floods = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.FLOOD)
                .collect(Collectors.toList());

        List<Incident> accidents = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.ACCIDENT)
                .collect(Collectors.toList());

        List<Incident> tornados = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.TORNADO)
                .collect(Collectors.toList());


        if (fires.size() > 0) {
            returnString.append("F");
            addIncidents(fires, returnString);
        }

        if (floods.size() > 0) {
            if (!returnString.toString().isEmpty()) {
                returnString.append("&");
            }

            returnString.append("I");
            addIncidents(floods, returnString);
        }

        if (accidents.size() > 0) {
            if (!returnString.toString().isEmpty()) {
                returnString.append("&");
            }

            returnString.append("A");
            addIncidents(accidents, returnString);
        }

        if (tornados.size() > 0) {
            if (!returnString.toString().isEmpty()) {
                returnString.append("&");
            }

            returnString.append("T");
            addIncidents(tornados, returnString);
        }

        return returnString.toString();
    }

    private void addIncidents(List<Incident> incidents, StringBuilder returnString) {
        for (Incident incident : incidents) {
            returnString.append("/");
            returnString.append(incident.getMapItem().getPosX());
            returnString.append(",");
            returnString.append(incident.getMapItem().getPosY());
            returnString.append(",");
            returnString.append(Math.round(incident.getIntensity()));
        }
    }

    private WebClient getWebClient() {
        if (this.webClient == null) {
            this.webClient = WebClient.builder()
                    .baseUrl(this.microbitUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }
        return this.webClient;
    }


}
