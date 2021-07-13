package it.italiancoders.helloworld;

import it.italiancoders.helloworld.service.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(HelloService helloService) {
		return (args) -> {
			helloService.getHelloMsg("pippo");

		};
	}

}
