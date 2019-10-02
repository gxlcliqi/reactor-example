package com.sunbird.core.example;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class FluxExample {
    public static void main(String[] args) {
//        Flux<Long> fibonacciGenerator = Flux.generate(
//                () -> Tuples.<Long, Long>of(0L, 1L),
//                (state, sink) -> {
//                    sink.next(state.getT1());
//                    System.out.println("generated "+state.getT1());
//                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
//                });
//        fibonacciGenerator.take(5).subscribe(t -> {
//            System.out.println("consuming "+t);
//            fibonacciSeries.add(t);
//        });
    }
}
