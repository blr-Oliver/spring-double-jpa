package org.example.doublejpa.infrastructure.primary.config;

import liquibase.integration.spring.SpringLiquibase;
import org.example.doublejpa.infrastructure.shared.DataSourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class PrimaryLiquibaseConfig {

  @Bean
  public SpringLiquibase primaryLiquibase(@Qualifier("primaryDataSource") DataSource primaryDatasource) {
    return DataSourceUtils.setupLiquibase(primaryDatasource, "classpath:db/liquibase/changelog.xml");
  }
}
