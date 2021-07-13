package it.italiancoders.helloworld.repository;

import it.italiancoders.helloworld.entity.HelloWorldMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloWorldMessageRepository extends JpaRepository<HelloWorldMessageEntity, Long> {
}
