package com.projettransversal.api.Seeder;

import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Repositories.BarrackRepository;
import com.projettransversal.api.Repositories.IncidentRepository;
import com.projettransversal.api.Repositories.MapItemRepository;
import com.projettransversal.api.Repositories.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Seeder.class);

    private final MapItemRepository _mapItemRepository;
    private final TruckRepository _truckRepository;
    private final IncidentRepository _incidentRepository;
    private final BarrackRepository _barrackRepository;

    public Seeder(MapItemRepository mapItemRepository,
                  TruckRepository truckRepository,
                  IncidentRepository incidentRepository,
                  BarrackRepository barrackRepository) {
        this._mapItemRepository = mapItemRepository;
        this._truckRepository = truckRepository;
        this._incidentRepository = incidentRepository;
        this._barrackRepository = barrackRepository;
    }

    @Override
    public void run(String... args) {
        DataTest data = new DataTest(_mapItemRepository);
        // loadIncidents(data.incidents);
        // loadBarracks(data.barracks);
        // loadTrucks(data.trucks);
    }

    private void loadIncidents(List<Incident> data) {
        if (_incidentRepository.count() == 0) {
            _incidentRepository.saveAll(data);
            logger.info(_incidentRepository.count() + " incidents inserted");
        }
    }

    private void loadBarracks(List<Barrack> data) {
        if (_barrackRepository.count() == 0) {
            _barrackRepository.saveAll(data);
            logger.info(_barrackRepository.count() + " barracks inserted");
        }
    }

    private void loadTrucks(List<Truck> data) {
        if (_truckRepository.count() == 0) {
            _truckRepository.saveAll(data);
            logger.info(_truckRepository.count() + " trucks inserted");
        }
    }
}
