package com.noit.server.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class DangerLevelsService {

    public List<DangerLevel> getDangerLevels() {
        DangerLevel level = new DangerLevel();
        level.setComment("Крысы");
        level.setLatitude(BigDecimal.valueOf(61.0041700));
        level.setLongitude(BigDecimal.valueOf(69.0019400));
        level.setLevel(1);
        return Collections.singletonList(level);
    }
}
