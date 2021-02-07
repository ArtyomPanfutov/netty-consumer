package devart.dummynettyconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Service
public class DummyConsumerService {
    private final WebClient webClient;

    @PostConstruct
    public void init () {
        System.out.println("GOGOGOGOG");
    }
    @Autowired
    public DummyConsumerService(WebClient webClient) {
        this.webClient = webClient;
    }

    public DummyDto[] get() {
        return webClient.get()
                .uri("http://localhost:8084/get")
                .retrieve()
                .bodyToMono(DummyDto[].class)
                .block();
    }
}
