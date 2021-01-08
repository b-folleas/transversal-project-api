package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Exception.IncidentNotFoundException;
import com.projettransversal.api.Exception.MapItemNotFoundException;
import com.projettransversal.api.Exception.TruckNotFoundException;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Models.ViewModels.TruckViewModel;

public interface ITruckService extends ICrudService<Truck> {

    Truck linkIncidentToTruck(int truck_id, int incident_id) throws TruckNotFoundException, IncidentNotFoundException;

    Truck create(TruckViewModel truckVM) throws MapItemNotFoundException;

    Truck moveTruck(int truck_id, int posX, int posY ) throws TruckNotFoundException ;

    }
