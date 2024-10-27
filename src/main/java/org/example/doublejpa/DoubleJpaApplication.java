package org.example.doublejpa;

import org.example.doublejpa.infrastructure.primary.jpa.repository.PrimaryMessageRepository;
import org.example.doublejpa.infrastructure.secondary.jpa.repository.SecondaryMessageRepository;
import org.example.doublejpa.infrastructure.shared.jpa.entity.MessageEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DoubleJpaApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(DoubleJpaApplication.class, args);
    if (false) {
      MessageEntity message = new MessageEntity();
      message.setAuthor("Oliver");
      message.setData("Hello");
      PrimaryMessageRepository primaryRepository = applicationContext.getBean(PrimaryMessageRepository.class);
      SecondaryMessageRepository secondaryRepository = applicationContext.getBean(SecondaryMessageRepository.class);
      primaryRepository.save(message);
      message.setData("world");
      secondaryRepository.save(message);
    }
  }
}
