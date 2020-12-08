package com.projettransversal.api.controllers;

import com.projettransversal.api.models.Fire;
import com.projettransversal.api.models.Ground;
import com.projettransversal.api.models.MapItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {

    @RequestMapping(value="/fires", method= RequestMethod.GET)
    public Fire getFires() {
        return new Fire(0, new MapItem(5,5, Ground.ROAD), 7);
    }
}
