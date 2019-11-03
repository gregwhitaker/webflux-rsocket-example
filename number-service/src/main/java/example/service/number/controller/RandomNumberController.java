package example.service.number.controller;

import example.model.NumberRequest;
import example.model.NumberResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

/**
 * Controller responsible for generating random numbers.
 */
@Controller
public class RandomNumberController {
    private static final Logger LOG = LoggerFactory.getLogger(RandomNumberController.class);

    private static final Random RAND = new Random(System.currentTimeMillis());

    /**
     * Get a random number.
     *
     * @return a {@link Mono} of a random number
     */
    @MessageMapping("randomNumber")
    public Mono<NumberResponse> randomNumber() {
        return Mono.fromSupplier(() -> new NumberResponse(RAND.nextInt(100 - 1 + 1) + 1));
    }

    /**
     * Get random numbers.
     *
     * @return a {@link Flux} of random numbers.
     */
    @MessageMapping("randomNumbers")
    public Flux<NumberResponse> randomNumbers(NumberRequest numberRequest) {
        return Flux.range(1, numberRequest.getNumberOfNumbers())
                .map(i -> {
                    int num = RAND.nextInt(100 - 1 + 1) + 1;

                    LOG.info("Generated Number: {}", num);

                    return new NumberResponse(num);
                });
    }
}
