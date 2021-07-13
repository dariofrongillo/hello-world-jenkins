package it.italiancoders.helloworld.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="hello_messages")
public class HelloWorldMessageEntity {
    @Id
    private Long id;

    private String message;

    private String language;
}
