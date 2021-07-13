package it.italiancoders.helloworld.service;

import java.util.Optional;

/**
 * Ciao master1
 */
public interface HelloService {
    Optional<String> getHelloMsg(String name);
}
