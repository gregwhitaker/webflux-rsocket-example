package example.service.letter.controller;

import example.model.LetterRequest;
import example.model.LetterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

/**
 * Controller responsible for generating random letters.
 */
@Controller
public class RandomLetterController {
    private static final Logger LOG = LoggerFactory.getLogger(RandomLetterController.class);

    private static final Random RAND = new Random(System.currentTimeMillis());

    /**
     * Get a random letter.
     *
     * @return a {@link Mono} of a random letter
     */
    @MessageMapping("randomLetter")
    public Mono<LetterResponse> randomLetter() {
        return Mono.fromSupplier(() -> new LetterResponse((char) (RAND.nextInt(26) + 'a')));
    }

    /**
     * Get a stream of random letters.
     *
     * @return a {@link Flux} of random letters
     */
    @MessageMapping("randomLetters")
    public Flux<LetterResponse> randomLetters(LetterRequest letterRequest) {
        return Flux.range(1, letterRequest.getNumberOfLetters())
                .map(i -> {
                    char c = (char) (RAND.nextInt(26) + 'a');

                    LOG.info("Generated Letter: {}", c);

                    return new LetterResponse(c);
                });
    }
}
