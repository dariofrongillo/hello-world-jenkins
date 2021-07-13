package it.italiancoders.helloworld.integration.service;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import it.italiancoders.helloworld.service.HelloService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import java.util.Optional;



@SpringBootTest
@ActiveProfiles("integration-test")
@DBRider
class HelloWorldServiceIntegrationTest {
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:11.1"); //creates the database for all tests on this file

    @Autowired
    private HelloService helloService;

    @BeforeClass
    public static void setupContainer() {
        postgres.start();
    }

    @AfterClass
    public static void shutdown() {
        postgres.stop();
    }

    @Test
    @DisplayName("Test  Hello Messages not Found")
    @DataSet("no_messages.yml")
    void testNotFoundMessages() {
        Optional<String> retval = helloService.getHelloMsg("Marco");
        Assertions.assertTrue(retval.isEmpty());
    }

    @Test
    @DisplayName("Test Hello Messages found but with message empty")
    @DataSet("empty_messages.yml")
    void testFoundMessagesAllEmpty() {
        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertTrue(message.isEmpty());
    }

    @Test
    @DisplayName("Test Hello Message Found")
    @DataSet("message.yml")
    void testMessageFound() {

        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertEquals(message.get(), "Ciao Marco");
    }

    @Test
    @DisplayName("Test more Hello Messages Found, the service should use the first")
    @DataSet("messages.yml")
    void testMessagesFound() {
        final Optional<String> message = helloService.getHelloMsg("Marco");
        Assertions.assertEquals(message.get(), "Ciao Marco");
    }


}
