package example.client.controller;

import example.client.model.ClientResponse;
import example.model.LetterRequest;
import example.model.LetterResponse;
import example.model.NumberRequest;
import example.model.NumberResponse;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Controller responsible for returning letters and numbers via SSE streams.
 */
@RestController
public class ClientController {

    @Autowired
    public RSocketRequester letterRSocketRequester;

    @Autowired
    public RSocketRequester numberRSocketRequester;

    /**
     * Get a stream of random letters.
     *
     * @return stream of random letters
     */
    @GetMapping(value = "/letters",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getLetters(@RequestParam(name = "count", defaultValue = "10") int count) {
        Flux<LetterResponse> randomLetters = letterRSocketRequester.route("randomLetters")
                .data(new LetterRequest(count))
                .retrieveFlux(LetterResponse.class);

        return randomLetters.map(letterResponse -> new ClientResponse(Character.toString(letterResponse.getLetter())))
                .delayElements(Duration.ofSeconds(1));
    }

    /**
     * Get a stream of random numbers.
     *
     * @return stream of random numbers
     */
    @GetMapping(value = "/numbers",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getNumbers(@RequestParam(name = "count", defaultValue = "10") int count) {
        Flux<NumberResponse> randomNumbers = numberRSocketRequester.route("randomNumbers")
                .data(new NumberRequest(count))
                .retrieveFlux(NumberResponse.class);

        return randomNumbers.map(numberResponse -> new ClientResponse(Integer.toString(numberResponse.getNumber())))
                .delayElements(Duration.ofSeconds(1));
    }

    /**
     * Get a stream of random letters combined with numbers (i.e. a15, c4, d45)
     *
     * @return stream of random letters combined with numbers
     */
    @GetMapping(value = "/both",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getNumbersAndLetters(@RequestParam(name = "count", defaultValue = "10") int count) {
        Flux<LetterResponse> randomLetters = letterRSocketRequester.route("randomLetters")
                .data(new LetterRequest(count))
                .retrieveFlux(LetterResponse.class);

        Flux<NumberResponse> randomNumbers = numberRSocketRequester.route("randomNumbers")
                .data(new NumberRequest(count))
                .retrieveFlux(NumberResponse.class);

        return Flux.zip(randomLetters, randomNumbers)
                .map(objects -> {
                    String value = objects.getT1().getLetter() + Integer.toString(objects.getT2().getNumber());
                    return new ClientResponse(value);
                })
                .delayElements(Duration.ofSeconds(1));
    }
}
