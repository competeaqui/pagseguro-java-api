package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.data.Amount;
import br.com.competeaqui.pagseguro.service.response.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Manoel Campos da Silva Filho
 * @see PixOrder
 */
@JsonSerialize
public record QrCode(
        String id,
        String text,
        Amount amount,

        // Exemplo: 2021-08-29T20:15:59-03:00
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
        OffsetDateTime expiration_date,

        List<String> arrangements,
        List<Link> links
) {
    /**
     * Construtor que cria um objeto PIX para enviar solicitação para o {@link PixOrderService}
     * para criação de QRCode.
     *
     * @param amountDecimal valor do PIX
     * @param expiration_date data de expiração
     */
    public QrCode(final String id, final String text, @NonNull final BigDecimal amountDecimal, final OffsetDateTime expiration_date) {
        this(nonBlank(id), nonBlank(text), new Amount(amountDecimal), expiration_date, null, null);
    }

    /**
     * Construtor que cria um objeto PIX para enviar solicitação para o {@link PixOrderService}
     * para criação de QRCode.
     *
     * @param amountDecimal valor do PIX
     * @param expiration_date data de expiração
     */
    public QrCode(@NonNull final BigDecimal amountDecimal, final OffsetDateTime expiration_date) {
        this(null, null, new Amount(amountDecimal), expiration_date, null, null);
    }

    /**
     * Construtor chamado automaticamente quando é feito o parse do JSON de resposta
     * de requisição para criação de QRCode PIX.
     * @see PixOrderResponse
     * @see PixOrderService
     */
    public QrCode(
        String id, String text, Amount amount, OffsetDateTime expiration_date,
        List<String> arrangements, List<Link> links)
    {
        this.id = id;
        this.text = text;
        this.amount = amount;
        this.expiration_date = expiration_date;
        this.arrangements = arrangements;
        this.links = links;
    }

    /**
     * Campos como id e text não podem ser atribuídos vazio
     * quando for enviar requisição para criação de QRCode.
     * Neste caso, devem ser nulo mesmo.
     * @param value
     * @return
     */
    private static String nonBlank(final String value){
        return value == null ? value : (value.isBlank() ? null : value);
    }
}
