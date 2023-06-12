package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.service.response.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.cdimascio.dotenv.Dotenv;
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
    private final String baseUri;
    private final String serviceUri;
    private final String token;

    private final HttpClient client;
    private final ObjectMapper jsonMapper;

    public PixOrderService() {
        final var env = Dotenv.load();
        baseUri = env.get("PAYMENT_SERVICE_URL");
        serviceUri = insertTraillingSlash(baseUri) + "orders";
        token = env.get("PAYMENT_SERVICE_TOKEN");

        client = HttpClient.newBuilder().build();
        jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
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
                                           .uri(URI.create(serviceUri))
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
