package it.italiancoders.helloworld.service.impl;

import it.italiancoders.helloworld.entity.HelloWorldMessageEntity;
import it.italiancoders.helloworld.repository.HelloWorldMessageRepository;
import it.italiancoders.helloworld.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class HelloServiceImpl implements HelloService {
    private final HelloWorldMessageRepository helloWorldMessageRepository;

    public HelloServiceImpl(HelloWorldMessageRepository helloWorldMessageRepository) {
        this.helloWorldMessageRepository = helloWorldMessageRepository;
    }

    @Override
    public Optional<String> getHelloMsg(String name) {

        List<HelloWorldMessageEntity> ss = this.helloWorldMessageRepository.findAll();

        Optional<String> helloMsg =  this.helloWorldMessageRepository.findAll()
                .stream()
                .filter((v) -> !ObjectUtils.isEmpty(v.getMessage()))
                .findAny()
                .map(HelloWorldMessageEntity::getMessage);

        return helloMsg.map((v) -> v + " " + name);

    }

}
