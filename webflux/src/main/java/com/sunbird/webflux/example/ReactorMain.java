package com.sunbird.webflux.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;


@SpringBootApplication
@Configuration
public class ReactorMain {
    public static void main(String[] args) {
        SpringApplication.run(ReactorMain.class, args);
        //readFibonacciNumbers();
    }

    public static void readFibonacciNumbers() {
        System.out.println("***************************** ");
        WebClient client = WebClient.create("http://localhost:8080");
        Flux<Long> result = client.get()
                .uri("/fibonacci")
                .retrieve()
                // .onStatus(HttpStatus::isError, x -> Mono.error(new RuntimeException("Invalid Response ")))
                .bodyToFlux(Long.class)
                .timeout(Duration.ofMillis(15000), x-> Flux.just(-1L));
                //.onErrorResume( x -> Flux.just(-1L, -2L))
                //.limitRequest(10L);
        result.subscribe( x-> System.out.println(x));
    }
}
