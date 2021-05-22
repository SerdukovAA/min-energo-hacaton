package com.noit.server.service;

import com.lowagie.text.DocumentException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    public void getReport(String city, OutputStream outputStream) throws IOException, DocumentException {
        String report = generateReport(city);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(report);
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    private String generateReport(String city) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("city", city);

        return templateEngine.process("report_template", context);
    }
}
