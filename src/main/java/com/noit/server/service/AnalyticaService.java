package com.noit.server.service;

import org.dizitart.no2.objects.ObjectRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class AnalyticaService {

    @Autowired
    private ObjectRepository<Analytica> repository;

    private int firstTableChildAsATable = 0;
    private int  timeIndex = 8;
    private int  dayRangesIndex = 8;
    private int  windIndexIndex = 5;
    private int  gustIndexIndex = 6;
    private int  humidIndexIndex = 7;
    private int  cloudsIndexIndex = 8;


    public Analytica get(String city) throws IOException {

        Document document = Jsoup.connect("https://meteo.paraplan.net/en/forecast/"+city+"/").get();
        Element element = document.getElementById("forecast");

        List<Element> currentDay = element
                .child(firstTableChildAsATable)
                .children()
                .stream()
                .filter(e -> e.hasClass("odd") || e.hasClass("even"))
                .limit(8)
                .collect(Collectors.toList());

        Analytica analytica = new Analytica();
        analytica.setCity(city);

        return analytica;
    }


}
