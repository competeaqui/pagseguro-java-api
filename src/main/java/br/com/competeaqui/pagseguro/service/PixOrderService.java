package br.com.competeaqui.pagseguro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NonNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers;

/**
 * Envia requisição de geração de QRCode de pagamento PIX.
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
public class PixOrderService {
    private static final String BASE_URI = "https://sandbox.api.pagseguro.com";
    private static final String SERVICE_URI = BASE_URI + "/orders";

    private final HttpClient client;
    private final ObjectMapper jsonMapper;

    public PixOrderService() {
        client = HttpClient.newBuilder().build();
        jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
    }

    /**
     * TODO Deve retornar um objeto {@link PixOrderResponse}
     * @throws RequestErrors
     */
    public String send(final @NonNull PixOrderRequest order){
        try {
            final String json = jsonMapper.writeValueAsString(order);
            System.out.printf("%s%n%n", json);
            final var request = HttpRequest.newBuilder()
                                           .POST(BodyPublishers.ofString(json))
                                           .uri(URI.create(SERVICE_URI))
                                           .build();
            final var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(res.statusCode() != 200) {
                throw jsonMapper.readValue(res.body(), RequestErrors.class);
            }
            return res.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
