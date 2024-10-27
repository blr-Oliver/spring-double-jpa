package org.example.doublejpa.infrastructure.secondary.jpa.repository;

import org.example.doublejpa.infrastructure.shared.jpa.repository.MessageRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryMessageRepository extends MessageRepository {
}
