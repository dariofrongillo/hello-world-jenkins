package it.italiancoders.helloworld.unit.service;

import it.italiancoders.helloworld.entity.HelloWorldMessageEntity;
import it.italiancoders.helloworld.repository.HelloWorldMessageRepository;
import it.italiancoders.helloworld.service.HelloService;
import it.italiancoders.helloworld.service.impl.HelloServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = {HelloServiceImpl.class})
public class HelloWorldServiceUnitTest {

    @Autowired
    private HelloService helloService;

    @MockBean
    private HelloWorldMessageRepository helloWorldMessageRepository;

    @Test
    @DisplayName("Test  Hello Messages not Found")
    void testNotFoundMessages() {
        List<HelloWorldMessageEntity> found = new ArrayList<>();
        doReturn(found).when(helloWorldMessageRepository).findAll();
        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertTrue(message.isEmpty());
    }

    @Test
    @DisplayName("Test Hello Messages found but with message empty")
    void testFoundMessagesAllEmpty() {
        List<HelloWorldMessageEntity> found = new ArrayList<>();
        found.add(HelloWorldMessageEntity.builder().id(1L).language("en").message(null).build());
        doReturn(found).when(helloWorldMessageRepository).findAll();
        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertTrue(message.isEmpty());
    }

    @Test
    @DisplayName("Test Hello Message Found")
    void testMessageFound() {
        List<HelloWorldMessageEntity> found = new ArrayList<>();
        found.add(HelloWorldMessageEntity.builder().id(1L).language("en").message("Hello World").build());
        doReturn(found).when(helloWorldMessageRepository).findAll();
        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertEquals(message.get(), "Hello World Marco");
    }

    @Test
    @DisplayName("Test more Hello Messages Found, the service should use the first")
    void testMessagesFound() {
        List<HelloWorldMessageEntity> found = new ArrayList<>();
        found.add(HelloWorldMessageEntity.builder().id(1L).language("en").message("Hello World").build());
        found.add(HelloWorldMessageEntity.builder().id(2L).language("en").message("Hello World2").build());

        doReturn(found).when(helloWorldMessageRepository).findAll();
        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertEquals(message.get(), "Hello World Marco");
    }

}
