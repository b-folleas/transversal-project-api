package com.projettransversal.api.Controllers;

import com.projettransversal.api.Models.Truck;
import com.projettransversal.api.Services.IServices.IFireService;
import com.projettransversal.api.Models.Fire;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value="/fire/{id}", method= RequestMethod.GET)
    public Optional<Fire> getFireById(@PathVariable int id) {
        return _fireService.findById(id);
    }
}
