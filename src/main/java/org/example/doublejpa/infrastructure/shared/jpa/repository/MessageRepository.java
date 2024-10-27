package org.example.doublejpa.infrastructure.shared.jpa.repository;

import org.example.doublejpa.infrastructure.shared.jpa.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
  List<MessageEntity> findByAuthor(String author);

  @Query("SELECT m FROM MessageEntity m WHERE m.author IS NULL")
  List<MessageEntity> findAnonymous();
}
