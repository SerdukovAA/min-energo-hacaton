package com.noit.server.rest;

import com.noit.server.service.Analytica;
import com.noit.server.service.AnalyticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AnalyticalRestController {

    @Autowired
    private AnalyticaService analyticaService;


    @GetMapping("/api/analytica/{city}")
    public Analytica get(@PathVariable String city) throws IOException {
       return analyticaService.get(city);
    }
}