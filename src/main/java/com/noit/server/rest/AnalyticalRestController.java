package com.noit.server.rest;

import com.noit.server.service.Analytica;
import com.noit.server.service.AnalyticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AnalyticalRestController {

    @Autowired
    private AnalyticaService analyticaService;

    //temperature   pressure   windspeed   cloudiness   precipitation
    private final String RESPONSE_TEMPLATE = "%s %s %s %s %s";

    @GetMapping("/weather/{city}")
    public String get(@PathVariable String city) throws IOException {
        Analytica analytica = analyticaService.get(city);
        return getResponse(analytica);
    }

    @GetMapping("/weather/{city}/{day}")
    public String get(@PathVariable String city,
                      @PathVariable int day) throws IOException {
        Analytica analytica = analyticaService.get(city, day);
        return getResponse(analytica);
    }

    @GetMapping("/weather/{city}/{day}/{time}")
    public String get(@PathVariable String city,
                      @PathVariable int day,
                      @PathVariable int time) throws IOException {
        Analytica analytica = analyticaService.get(city, day, time);
        return getResponse(analytica);
    }

    private String getResponse(Analytica analytica) {
        return String.format(RESPONSE_TEMPLATE,
                analytica.getTemperature(),
                analytica.getPressure(),
                analytica.getWindspeed(),
                analytica.getCloudiness(),
                analytica.getPrecipitation()
        );
    }

}