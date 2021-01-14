package com.projettransversal.api.Controllers;

import com.projettransversal.api.Exception.MapItemNotFoundException;
import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.ViewModels.BarrackViewModel;
import com.projettransversal.api.Services.IServices.IBarrackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BarrackController {

    private final IBarrackService _barrackService;

    public BarrackController(IBarrackService barrackService) {
        _barrackService = barrackService;
    }

    @RequestMapping(value = "/barracks", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération de toutes la barracks")
    public List<Barrack> getBarracks() {
        return _barrackService.findAll();
    }

    @RequestMapping(value = "/barrack/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Récupération d'une barrack en fonction de son ID")
    public Optional<Barrack> getBarrackById(@PathVariable Long id) {
        return _barrackService.findById(id);
    }

    @RequestMapping(value = "/barrack", method = RequestMethod.POST)
    @ApiOperation(value = "Création d'une barrack à travers l'objet BarrackViewModel")
    public Barrack createBarrack(@RequestBody BarrackViewModel barrackVM) throws MapItemNotFoundException {
        return this._barrackService.create(barrackVM);
    }


}
