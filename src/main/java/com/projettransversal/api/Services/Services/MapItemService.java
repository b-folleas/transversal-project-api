package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Repositories.MapItemRepository;
import com.projettransversal.api.Services.IServices.IMapItemService;
import org.springframework.stereotype.Service;

@Service
public class MapItemService extends CrudService<MapItem> implements IMapItemService {

    private final MapItemRepository _mapItemRepository;

    public MapItemService(MapItemRepository mapItemRepository) {
        super(mapItemRepository);
        _mapItemRepository = mapItemRepository;
    }

    public MapItem findByCoordinates(int x, int y) {
        return _mapItemRepository.findByCoordinates(x, y);
    }
}
