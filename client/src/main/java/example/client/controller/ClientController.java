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

@RestController
public class ClientController {

    @Autowired
    public RSocketRequester letterRSocketRequester;

    @Autowired
    public RSocketRequester numberRSocketRequester;

    /**
     *
     * @return
     */
    @GetMapping(value = "/letters",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getLetters(@RequestParam(name = "count", defaultValue = "10") int count) {
        Flux<LetterResponse> randomLetters = letterRSocketRequester.route("randomLetters")
                .data(new LetterRequest(count))
                .retrieveFlux(LetterResponse.class);

        return randomLetters.map(letterResponse -> new ClientResponse(Character.toString(letterResponse.getLetter())));
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/numbers",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getNumbers(@RequestParam(name = "count", defaultValue = "10") int count) {
        Flux<NumberResponse> randomNumbers = numberRSocketRequester.route("randomNumbers")
                .data(new NumberRequest(count))
                .retrieveFlux(NumberResponse.class);

        return randomNumbers.map(numberResponse -> new ClientResponse(Integer.toString(numberResponse.getNumber())));
    }

    /**
     *
     * @return
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

        return null;
    }
}
