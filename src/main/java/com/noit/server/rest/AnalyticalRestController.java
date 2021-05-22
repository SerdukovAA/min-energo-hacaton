package com.noit.server.rest;

import com.noit.server.service.Analytica;
import com.noit.server.service.AnalyticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AnalyticalRestController {

    @Autowired
    private AnalyticaService analyticaService;

    //temperature   pressure   windspeed   cloudiness   precipitation
    private final String RESPONSE_TEMPLATE = "%s %s %s %s %s";

    @GetMapping("/weather/{city}")
    public String get(@PathVariable String city) throws IOException {
       Analytica analytica = analyticaService.get(city);
       return String.format(RESPONSE_TEMPLATE,
               analytica.getTemperature(),
               analytica.getPressure(),
               analytica.getWindspeed(),
               analytica.getCloudiness(),
               analytica.getPrecipitation()
       );
    }
}