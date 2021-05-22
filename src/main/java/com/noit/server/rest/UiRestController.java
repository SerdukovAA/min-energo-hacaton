package com.noit.server.rest;

import com.lowagie.text.DocumentException;
import com.noit.server.service.DangerLevel;
import com.noit.server.service.DangerLevelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class UiRestController {

    @Autowired
    private DangerLevelsService dangerLevelsService;

    @GetMapping("/levels")
    public List<DangerLevel> getDangerLevels() {
        return dangerLevelsService.getDangerLevels();
    }

    @RequestMapping(value = "/files/{city}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("city") String city,
            HttpServletResponse response) {
        try {
            dangerLevelsService.getReport(city, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException | DocumentException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}