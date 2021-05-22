package com.noit.server.rest;

import com.noit.server.service.DangerLevel;
import com.noit.server.service.DangerLevelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UiRestController {

    @Autowired
    private DangerLevelsService dangerLevelsService;

    @GetMapping("/levels")
    public List<DangerLevel> getDangerLevels() {
        return dangerLevelsService.getDangerLevels();
    }
}