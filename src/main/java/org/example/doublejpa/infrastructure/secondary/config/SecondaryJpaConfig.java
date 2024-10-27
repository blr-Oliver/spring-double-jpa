package org.example.doublejpa.infrastructure.secondary.config;

import org.example.doublejpa.infrastructure.secondary.jpa.repository.SecondaryMessageRepository;
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
    basePackageClasses = SecondaryMessageRepository.class,
    entityManagerFactoryRef = "secondaryEntityManagerFactory",
    transactionManagerRef = "secondaryTxManager"
)
public class SecondaryJpaConfig {
  @Bean
  public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
      @Qualifier("secondaryDataSource") DataSource secondaryDataSource,
      EntityManagerFactoryBuilder builder
  ) {
    return DataSourceUtils.createEntityManagerFactory(secondaryDataSource, builder, new String[]{MessageEntity.class.getPackageName()});
  }

  @Bean
  public PlatformTransactionManager secondaryTxManager(@Qualifier("secondaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory) {
    return new JpaTransactionManager(Objects.requireNonNull(secondaryEntityManagerFactory.getObject()));
  }
}
