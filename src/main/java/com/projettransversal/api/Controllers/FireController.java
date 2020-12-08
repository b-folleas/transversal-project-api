package com.projettransversal.api.Controllers;

import com.projettransversal.api.Services.IServices.IFireService;
import com.projettransversal.api.Models.Fire;
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
