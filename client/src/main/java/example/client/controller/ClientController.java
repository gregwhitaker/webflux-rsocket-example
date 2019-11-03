package example.client.controller;

import example.client.model.ClientResponse;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/numbers",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getNumbers() {
        return null;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/letters",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getLetters() {
        return null;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/numbersletters",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<ClientResponse> getNumbersAndLetters() {
        return null;
    }
}
