package org.example.doublejpa.infrastructure.shared;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.UpdateSummaryEnum;
import liquibase.UpdateSummaryOutputEnum;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.ui.UIServiceEnum;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceUtils {
  public static DataSource createHikariDataSource(DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  public static SpringLiquibase setupLiquibase(DataSource dataSource, LiquibaseProperties props) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog(props.getChangeLog());
    liquibase.setClearCheckSums(props.isClearChecksums());
    liquibase.setContexts(props.getContexts());
    liquibase.setDefaultSchema(props.getDefaultSchema());
    liquibase.setLiquibaseSchema(props.getLiquibaseSchema());
    liquibase.setLiquibaseTablespace(props.getLiquibaseTablespace());
    liquibase.setDatabaseChangeLogTable(props.getDatabaseChangeLogTable());
    liquibase.setDatabaseChangeLogLockTable(props.getDatabaseChangeLogLockTable());
    liquibase.setDropFirst(props.isDropFirst());
    liquibase.setShouldRun(props.isEnabled());
    liquibase.setLabelFilter(props.getLabelFilter());
    liquibase.setChangeLogParameters(props.getParameters());
    liquibase.setRollbackFile(props.getRollbackFile());
    liquibase.setTestRollbackOnUpdate(props.isTestRollbackOnUpdate());
    liquibase.setTag(props.getTag());
    if (props.getShowSummary() != null)
      liquibase.setShowSummary(UpdateSummaryEnum.valueOf(props.getShowSummary().name()));
    if (props.getShowSummaryOutput() != null)
      liquibase.setShowSummaryOutput(UpdateSummaryOutputEnum.valueOf(props.getShowSummaryOutput().name()));
    if (props.getUiService() != null)
      liquibase.setUiService(UIServiceEnum.valueOf(props.getUiService().name()));
    return liquibase;
  }

  public static SpringLiquibase setupLiquibase(DataSource dataSource, String changeLog) {
    LiquibaseProperties liquibaseProperties = new LiquibaseProperties();
    liquibaseProperties.setChangeLog(changeLog);
    return setupLiquibase(dataSource, liquibaseProperties);
  }

  public static LocalContainerEntityManagerFactoryBean createEntityManagerFactory(DataSource dataSource, EntityManagerFactoryBuilder builder, String[] baseEntityPackages) {
    Properties properties = new Properties();
    properties.put("hibernate.hbm2ddl.auto", "validate");
    properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
    properties.put("hibernate.show_sql", true);
    properties.put("hibernate.format_sql", true);
    LocalContainerEntityManagerFactoryBean emf = builder
        .dataSource(dataSource)
        .packages(baseEntityPackages)
        .build();
    emf.setJpaProperties(properties);
    return emf;
  }
}
