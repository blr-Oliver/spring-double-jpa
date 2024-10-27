package org.example.doublejpa.infrastructure.primary.config;

import org.example.doublejpa.infrastructure.primary.jpa.repository.PrimaryMessageRepository;
import org.example.doublejpa.infrastructure.shared.DataSourceUtils;
import org.example.doublejpa.infrastructure.shared.jpa.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@EnableJpaRepositories(
    basePackageClasses = PrimaryMessageRepository.class,
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTxManager"
)
public class PrimaryJpaConfig {
  @Bean
  public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
      @Qualifier("primaryDataSource") DataSource primaryDataSource,
      EntityManagerFactoryBuilder builder
  ) {
    return DataSourceUtils.createEntityManagerFactory(primaryDataSource, builder, new String[]{MessageEntity.class.getPackageName()});
  }

  @Bean
  public PlatformTransactionManager primaryTxManager(@Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
    return new JpaTransactionManager(Objects.requireNonNull(primaryEntityManagerFactory.getObject()));
  }
}
