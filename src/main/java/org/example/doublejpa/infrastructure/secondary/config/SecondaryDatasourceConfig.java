package org.example.doublejpa.infrastructure.secondary.config;

import org.example.doublejpa.infrastructure.shared.DataSourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class SecondaryDatasourceConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.secondary-datasource")
  public DataSourceProperties secondaryDatasourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.secondary-datasource.hikari")
  public DataSource secondaryDataSource(@Qualifier("secondaryDatasourceProperties") DataSourceProperties defaultDataSourceProperties) {
    return DataSourceUtils.createHikariDataSource(defaultDataSourceProperties);
  }
}
