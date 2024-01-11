package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.Util;
import br.com.competeaqui.pagseguro.service.response.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NonNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
        this.serviceUrl = Util.validateUrl(baseUrl + "/orders");
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
            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final var status = String.valueOf(response.statusCode());
            final var responseBody = response.body();
            print(request, json);
            print(response);
            if(!status.startsWith("20")) {
                throw jsonMapper.readValue(responseBody, ResponseError.class);
            }
            return jsonMapper.readValue(responseBody, PixOrder.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(final HttpRequest request, final String jsonBody) {
        println("Request");
        println("curl --location --request %s %s \\", request.method(), request.uri());
        printHeader(request.headers(), "Authorization", "-H ");
        println("-H 'Content-Type: application/json' \\");
        println("--data-raw '%s'", jsonBody);
        System.out.println();
    }

    private static void printHeader(final HttpHeaders headers, final String headerName) {
        printHeader(headers, headerName, "");
    }

    private static void printHeader(final HttpHeaders headers, final String headerName, final String prefix) {
        final var quotes = prefix.isBlank() ? "" : "'";
        final var breakLine = prefix.isBlank() ? "" : " \\";
        headers.firstValue(headerName).ifPresent(val -> println("%s%s%s: %s%s%s", prefix, quotes, headerName, val, quotes, breakLine));
    }

    private void print(final HttpResponse<String> response) {
        System.out.println("Response");
        printHeader(response.headers(), ":status");
        printHeader(response.headers(), "content-type");
        System.out.println();
        println(response.body());
        System.out.println();
    }

    private static void println(final String format, final Object ...args){
        System.out.printf(format + "%n", args);
    }
}
