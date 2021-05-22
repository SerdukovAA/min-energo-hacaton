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
        return get(city, CURRENT_DAY, LocalTime.now(ZoneId.of("Europe/Moscow"))
                .format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    public Analytica get(String city, int day) throws IOException {
        return get(city, day, "00:00");
    }

    public Analytica get(String city, int day, int hours) throws IOException {
        return get(city, day,
                LocalTime.parse(String.valueOf(hours), DateTimeFormatter.ofPattern("H"))
                        .format(DateTimeFormatter.ofPattern("HH:mm"))
        );
    }

    private Analytica get(String city, int day, String time) throws IOException {
        List<Element> currentDay = getDayData(city, day);
        List<Analytica> dayAnalytica = currentDay.stream()
                .map(e -> mapToAnalytica(city, e))
                .sorted(Comparator.comparing(Analytica::getTime))
                .collect(toList());

        if(time.equals("00:00")){
            return dayAnalytica.get(0);
        }
        return dayAnalytica.stream()
                .filter(a -> time.compareTo(a.getTime()) < 0)
                .findFirst().orElse(new Analytica());
    }

    @NotNull
    private List<Element> getDayData(String city, int day) throws IOException {
        Document document = Jsoup.connect(String.format(GEO_SERVICE_URL, city)).get();
        Element element = document.getElementById("forecast");

        return element
                .child(0)
                .children()
                .stream()
                .filter(e -> e.hasClass("odd") || e.hasClass("even"))
                .skip(day*8)
                .limit(8)
                .collect(Collectors.toList());
    }

    @NotNull
    private Analytica mapToAnalytica(String city, Element e) {
        Analytica analytica = new Analytica();
        List<Node> fields = e.childNodes()
                .stream()
                .filter(n ->
                        !((Element) n).hasClass("date")
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
