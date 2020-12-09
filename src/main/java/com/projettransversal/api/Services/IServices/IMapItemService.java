package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Models.MapItem;

public interface IMapItemService extends ICrudService<MapItem> {
    MapItem findByCoordinates(int x, int y);
}
