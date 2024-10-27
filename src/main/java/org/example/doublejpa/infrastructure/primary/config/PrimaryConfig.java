package org.example.doublejpa.infrastructure.primary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    PrimaryDatasourceConfig.class,
    PrimaryLiquibaseConfig.class,
    PrimaryJpaConfig.class
})
public class PrimaryConfig {
}
