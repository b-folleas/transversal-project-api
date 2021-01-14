package com.projettransversal.api.Controllers;

import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Services.IServices.IMapItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MapItemController {

    private final IMapItemService _truckService;

    public MapItemController(IMapItemService truckService) {
        _truckService = truckService;
    }

    @RequestMapping(value = "/mapItems", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération de tous les mapItems")
    public List<MapItem> getMapItems() {
        return _truckService.findAll();
    }

    @RequestMapping(value = "/mapItem/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération d'un mapItemp en fonction de son ID")
    public Optional<MapItem> getMapItemById(@PathVariable Long id) {
        return _truckService.findById(id);
    }

}
