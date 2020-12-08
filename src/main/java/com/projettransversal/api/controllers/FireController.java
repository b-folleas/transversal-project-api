package com.projettransversal.api.controllers;

import com.projettransversal.api.IServices.IFireService;
import com.projettransversal.api.Services.FireService;
import com.projettransversal.api.models.Fire;
import com.projettransversal.api.models.Ground;
import com.projettransversal.api.models.MapItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireController {
    private final IFireService _fireService;

    public FireController(IFireService fireService) {
        _fireService = fireService;
    }

    @RequestMapping(value="/fires", method= RequestMethod.GET)
    public List<Fire> getFires() {
        return _fireService.findAll();
    }
}
