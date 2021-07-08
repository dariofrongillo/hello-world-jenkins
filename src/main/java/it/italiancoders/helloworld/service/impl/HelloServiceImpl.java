package it.italiancoders.helloworld.service.impl;

import it.italiancoders.helloworld.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String getHelloMsg(String name) {
        return "Hello " + name;
    }
}
