# webflux-rsocket-example
An example of building Spring WebFlux services using RSocket as the communication protocol.

The example exposes two RSocket services via Spring WebFlux and streams random letter and number data to a web broswer via SSE streaming.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build
    
## Running the Example
Follow the steps below to run the example:

1. Run the following command to start the `letter-service`:

        ./gradlew :letter-service:bootRun

2. In a new terminal, run the following command to start the `number-service`:

        ./gradlew :number-service:bootRun
        
3. In a new terminal, run the following command to start the `client`:

        ./gradlew :client:bootRun
        
4. In a web browser, navigate to `http://localhost:8080/letters` to receive a stream of `10` random letters with a delay of one second.

5. In a web browser, navigate to `http://localhost:8080/numbers` to receive a stream of `10` random numbers with a delay of one second.

6. In a web browser, navigate to `http://localhost:8080/numbers?count=5` to receive a stream of `5` random numbers with a delay of one second.

7. In a web browser, navigate to `http://localhost:8080/both` to receive a stream of `10` pairs of random letter and number combinations with a delay of one second.

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/webflux-rsocket-example/issues).

## License
MIT License

Copyright (c) 2019 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.