package org.example.doublejpa.infrastructure.primary.config;

import org.example.doublejpa.infrastructure.shared.DataSourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class PrimaryDatasourceConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSourceProperties primaryDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties primaryDataSourceProperties) {
    return DataSourceUtils.createHikariDataSource(primaryDataSourceProperties);
  }
}
