package com.noit.server.config;

import com.noit.server.service.Analytica;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfiguration {

    @Value("${storage_path}")
    private String storagePath;

    @Bean(destroyMethod = "close")
    public ObjectRepository<Analytica> analyticaRepository() {
        Nitrite db = Nitrite.builder()
                .compressed().filePath(storagePath)
                .openOrCreate();
        return db.getRepository(Analytica.class);
    }

}
