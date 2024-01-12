package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.data.Amount;
import br.com.competeaqui.pagseguro.service.response.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Representa um QRCode PIX.
 * @author Manoel Campos da Silva Filho
 * @param amount valor do PIX em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
 * @param expiration_date data de validade do QRCode (após esta data, ele não aceita mais pagamentos)
 * @param text código Copia e Cola para pagamento
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
     * @param amount valor do PIX em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
     * @param expiration_date data de validade do QRCode (após esta data, ele não aceita mais pagamentos).
     */
    public QrCode(final String id, final String text, final int amount, final OffsetDateTime expiration_date) {
        this(nonBlank(id), nonBlank(text), new Amount(amount), expiration_date, null, null);
    }

    /**
     * Construtor que cria um objeto PIX para enviar solicitação para o {@link PixOrderService}
     * para criação de QRCode.
     *
     * @param amout valor do PIX em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
     * @param expiration_date data de validade do QRCode (após esta data, ele não aceita mais pagamentos).
     */
    public QrCode(final int amout, final OffsetDateTime expiration_date) {
        this(null, null, new Amount(amout), expiration_date, null, null);
    }

    /**
     * Construtor chamado automaticamente quando é feito o parse do JSON de resposta
     * de requisição para criação de QRCode PIX.
     * @param amount valor do PIX em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
     * @see PixOrder
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
