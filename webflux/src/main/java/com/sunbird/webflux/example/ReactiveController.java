package com.sunbird.webflux.example;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;

@RestController
public class ReactiveController {

    @GetMapping("/")
    public String handle(Model model) {
        model.addAttribute("text", "Hello WebFlux");
        return "home.html";
    }

    @GetMapping("/freemarker")
    public String handleFreemarker(Model model) {
        model.addAttribute("text", "Hello WebFlux");
        return "home";
    }

    @GetMapping("/text")
    @ResponseBody
    public Publisher<String> handler() {
        return Flux.just("Hello world!", "This is from webflux");
    }

    @GetMapping("/numbers")
    public String handleSeries(Model model) {
        Flux<Long> fibonacciGenerator = Flux.generate(() -> Tuples.<Long,
                Long>of(0L, 1L), (state, sink) -> {
            if (state.getT1() < 0)
                sink.complete();
            else
                sink.next(state.getT1());
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
        model.addAttribute("series", fibonacciGenerator.collectList());
        return "numbers";
    }

    @GetMapping(value = "/numbers1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long>  handleSeries1() {
        Flux<Long> fibonacciGenerator = Flux.generate(() -> Tuples.<Long,
                Long>of(0L, 1L), (state, sink) -> {
            if (state.getT1() < 0)
                sink.complete();
            else
                sink.next(state.getT1());
            System.out.println("numbers1 generated :"+state.getT1());
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
        return fibonacciGenerator.delayElements(Duration.ofSeconds(1));
    }
}
