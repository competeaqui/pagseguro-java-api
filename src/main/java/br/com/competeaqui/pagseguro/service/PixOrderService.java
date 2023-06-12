package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.service.response.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NonNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static br.com.competeaqui.pagseguro.Util.insertTraillingSlash;
import static java.net.http.HttpRequest.BodyPublishers;

/**
 * Envia requisição de geração de QRCode de pagamento PIX.
 * @author Manoel Campos da Silva Filho
 * @see PixOrder
 */
public class PixOrderService {
    /** Exemplo: 2023-06-10T20:53:13.471-03:00 */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";
    private final String serviceUrl;
    private final String token;

    private final HttpClient client;
    private final ObjectMapper jsonMapper;

    public PixOrderService(@NonNull final String baseUrl, @NonNull final String token) {
        this.serviceUrl = insertTraillingSlash(baseUrl) + "orders";
        this.token = token;
        this.client = HttpClient.newBuilder().build();
        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.registerModule(new JavaTimeModule());
    }

    /**
     * @throws ResponseError quando ocorrem erros no tratamento da requisição
     */
    public PixOrder send(final @NonNull PixOrder order){
        try {
            final String json = jsonMapper.writeValueAsString(order);
            final var request = HttpRequest.newBuilder()
                                           .POST(BodyPublishers.ofString(json))
                                           .header("Authorization", token)
                                           .uri(URI.create(serviceUrl))
                                           .build();
            System.out.printf("%n%s%n%n", json);
            final var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            final var status = String.valueOf(res.statusCode());
            final var responseBody = res.body();
            if(!status.startsWith("20")) {
                throw jsonMapper.readValue(responseBody, ResponseError.class);
            }
            return jsonMapper.readValue(responseBody, PixOrder.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
