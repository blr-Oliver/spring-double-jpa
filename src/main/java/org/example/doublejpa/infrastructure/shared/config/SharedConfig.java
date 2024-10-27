package org.example.doublejpa.infrastructure.shared.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilderCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Configuration
public class SharedConfig {
  @Bean
  public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaVendorAdapter jpaVendorAdapter,
                                                                 ObjectProvider<JpaProperties> jpaProperties,
                                                                 ObjectProvider<PersistenceUnitManager> persistenceUnitManager,
                                                                 ObjectProvider<EntityManagerFactoryBuilderCustomizer> customizers) {
    Map<String, String> jpaPropertiesMap = Optional.ofNullable(jpaProperties.getIfAvailable())
        .map(JpaProperties::getProperties)
        .orElse(Collections.emptyMap());
    EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(jpaVendorAdapter,
        jpaPropertiesMap,
        persistenceUnitManager.getIfAvailable());
    customizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
    return builder;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }
}
