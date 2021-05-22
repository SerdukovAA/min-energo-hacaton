package com.noit.server.service;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class AnalyticaService {

    private String GEO_SERVICE_URL = "https://meteo.paraplan.net/en/forecast/%s/";

    private final int CURRENT_DAY = 0;
    private final int TIME_INDEX = 0;
    private final int WIND_INDEX = 4;
    private final int TEMP_INDEX = 1;
    private final int PREC_INDEX = 8;
    private final int CLOUDS_INDEX = 7;
    private final int PRESS_INDEX = 9;

    public Analytica get(String city) throws IOException {
        Document document = Jsoup.connect(String.format(GEO_SERVICE_URL, city)).get();
        Element element = document.getElementById("forecast");

        List<Element> currentDay = element
                .child(CURRENT_DAY)
                .children()
                .stream()
                .filter(e -> e.hasClass("odd") || e.hasClass("even"))
                .limit(8)
                .collect(Collectors.toList());

        String now = LocalTime.now(ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("HH:mm"));
        List<Analytica> dayAnalytica = currentDay.stream()
                .map(e -> mapToAnalytica(city, e))
                .collect(toList());

        return dayAnalytica.stream().sorted(Comparator.comparing(Analytica::getTime))
                .filter(a -> now.compareTo(a.getTime()) < 0)
                .findFirst().orElse(new Analytica());
    }

    @NotNull
    private Analytica mapToAnalytica(String city, Element e) {
        Analytica analytica = new Analytica();
        List<Node> fields = e.childNodes()
                .stream()
                .filter(n ->
                        !((Element) n).hasClass("date weekend")
                )
                .collect(Collectors.toList());

        analytica.setTime(((Element) fields.get(TIME_INDEX)).text());
        analytica.setTemperature(((Element) fields.get(TEMP_INDEX)).text());
        analytica.setWindspeed(((Element) fields.get(WIND_INDEX)).text());
        analytica.setCloudiness(((Element) fields.get(CLOUDS_INDEX)).text());
        analytica.setPrecipitation(((Element) fields.get(PREC_INDEX)).text());
        analytica.setPressure(((Element) fields.get(PRESS_INDEX)).text());
        analytica.setCity(city);
        return analytica;
    }

}
