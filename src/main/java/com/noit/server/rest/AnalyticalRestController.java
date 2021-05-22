package com.noit.server.rest;

import com.noit.server.service.Analytica;
import com.noit.server.service.AnalyticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnalyticalRestController {

    @Autowired
    private AnalyticaService analyticaService;


    @GetMapping("/api/analytica/{region}/{city}")
    public Analytica get(
            @PathVariable String region,
            @PathVariable String city) {
       return analyticaService.get(region, city);
    }
}