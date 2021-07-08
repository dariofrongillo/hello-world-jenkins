package it.italiancoders.helloworld;

import it.italiancoders.helloworld.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class HelloWorldApplicationTests {

    @Autowired
    private HelloService helloService;


    @Test
    @DisplayName("Test Hello message")
    void testFindProductByIdFound() {
        final String message = helloService.getHelloMsg("Marco");
        Assertions.assertEquals(message, "Hello Marco");
    }

}
