package org.example.doublejpa.infrastructure.secondary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    SecondaryDatasourceConfig.class,
    SecondaryLiquibaseConfig.class,
    SecondaryJpaConfig.class
})
public class SecondaryConfig {
}
