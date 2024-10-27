package org.example.doublejpa.infrastructure.secondary.config;

import liquibase.integration.spring.SpringLiquibase;
import org.example.doublejpa.infrastructure.shared.DataSourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class SecondaryLiquibaseConfig {

  @Bean
  public SpringLiquibase secondaryLiquibase(@Qualifier("secondaryDataSource") DataSource secondaryDatasource) {
    return DataSourceUtils.setupLiquibase(secondaryDatasource, "classpath:db/liquibase/changelog.xml");
  }
}
