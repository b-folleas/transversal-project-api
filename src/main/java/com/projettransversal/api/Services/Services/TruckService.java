package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Exception.IncidentNotFoundException;
import com.projettransversal.api.Exception.MapItemNotFoundException;
import com.projettransversal.api.Exception.TruckNotFoundException;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Models.ViewModels.TruckViewModel;
import com.projettransversal.api.ProjetTransversalExceptionEnum;
import com.projettransversal.api.Repositories.TruckRepository;
import com.projettransversal.api.Services.IServices.IIncidentService;
import com.projettransversal.api.Services.IServices.IMapItemService;
import com.projettransversal.api.Services.IServices.ITruckService;
import org.springframework.stereotype.Service;

@Service
public class TruckService extends CrudService<Truck> implements ITruckService {

    private final IIncidentService incidentService;
    private final IMapItemService mapItemService;

    public TruckService(TruckRepository truckRepository, IIncidentService incidentService, IMapItemService mapItemService) {
        super(truckRepository);
        this.incidentService = incidentService;
        this.mapItemService = mapItemService;
    }

    public Truck linkIncidentToTruck(int truck_id, int incident_id) throws TruckNotFoundException, IncidentNotFoundException {
        Truck truck = this.findById(truck_id).orElseThrow(() -> new TruckNotFoundException(ProjetTransversalExceptionEnum.TRUCK_NOT_FOUND, truck_id));
        Incident incident = this.incidentService.findById(incident_id).orElseThrow(() -> new IncidentNotFoundException(ProjetTransversalExceptionEnum.INCIDENT_NOT_FOUND, incident_id));

        truck.getIncidents().add(incident);
        truck.setAvailability(false);
        return this.insertOrUpdate(truck);
    }

    public Truck moveTruck(int truck_id, int posX, int posY ) throws  TruckNotFoundException {
        MapItem mapItem = mapItemService.findByCoordinates(posX, posY);
        Truck truck = this.findById(truck_id).orElseThrow(() -> new TruckNotFoundException(ProjetTransversalExceptionEnum.TRUCK_NOT_FOUND, truck_id));
        truck.setMapItem(mapItem);
        return this.insertOrUpdate(truck);
    }

    public Truck create(TruckViewModel truckVM) throws MapItemNotFoundException {
        Truck truck = truckVM.toModel(this.mapItemService).orElseThrow(() -> new MapItemNotFoundException(ProjetTransversalExceptionEnum.MAPITEM_NOT_FOUND, truckVM.getPosX(), truckVM.getPosY()));
        return this.insertOrUpdate(truck);
    }
}
