package com.noit.server.service;

import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class AnalyticaService {

    @Autowired
    private ObjectRepository<Analytica> repository;

    public Analytica get(String region, String city) {
           return null;
    }

}
