package com.projettransversal.api.Controllers;

import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Services.IServices.IBarrackService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BarrackController {

    private final IBarrackService _barrackService;

    public BarrackController(IBarrackService barrackService) {
        _barrackService = barrackService;
    }

    @RequestMapping(value="/barracks", method= RequestMethod.GET)
    public List<Barrack> getBarracks() {
        return _barrackService.findAll();
    }

    @RequestMapping(value="/barrack/{id}", method= RequestMethod.GET)
    public Optional<Barrack> getBarrackById(@PathVariable int id) {
        return _barrackService.findById(id);
    }

}
