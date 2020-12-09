package com.projettransversal.api.Helpers;

import com.projettransversal.api.DAL.Repositories.FireRepository;
import com.projettransversal.api.DAL.Repositories.MapItemRepository;
import com.projettransversal.api.DAL.Repositories.TruckRepository;
import com.projettransversal.api.Models.Fire;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Models.Truck;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    MapItemRepository _mapItemRepository;
    TruckRepository _truckRepository;
    FireRepository _fireRepository;

    public Seeder(MapItemRepository mapItemRepository,
                  TruckRepository truckRepository,
                  FireRepository fireRepository) {
        this._mapItemRepository = mapItemRepository;
        this._truckRepository = truckRepository;
        this._fireRepository = fireRepository;
    }

    @Override
    public void run(String... args) {
        DataTest data = new DataTest();
        try {
            loadMapItem(data.mapItems);
            loadFires(data.fires);
            loadTrucks(data.trucks);
        }
        catch (Exception ignored) {
        }
    }

    private void loadMapItem(List<MapItem> data) {
        if (_mapItemRepository.count() == 0) {
            _mapItemRepository.saveAll(data);

            System.out.println(_mapItemRepository.count() + " mapItems inserted");
        }
    }

    private void loadFires(List<Fire> data) {
        if (_fireRepository.count() == 0) {
            _fireRepository.saveAll(data);

            System.out.println(_fireRepository.count() + " fires inserted");
        }
    }

    private void loadTrucks(List<Truck> data) {
        if (_truckRepository.count() == 0) {
            _truckRepository.saveAll(data);

            System.out.println(_truckRepository.count() + " trucks inserted");
        }
    }
}
