package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.Util;
import br.com.competeaqui.pagseguro.data.Amount;
import br.com.competeaqui.pagseguro.service.response.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
@Getter @Setter @NoArgsConstructor @ToString
public class QrCode {
    private String id;
    private String text;
    private Amount amount = Amount.zero();

    // Exemplo: 2021-08-29T20:15:59-03:00
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
    private OffsetDateTime expiration_date;

    private List<String> arrangements;
    private List<Link> links;

    /**
     * Construtor para criar um objeto PIX para enviar solicitação para o {@link PixOrderService}
     * para criação de QRCode.
     *
     * @param amountDecimal valor do PIX
     * @param expiration_date data de expiração
     */
    public QrCode(@NonNull final BigDecimal amountDecimal, final OffsetDateTime expiration_date) {
        this(new Amount(amountDecimal), expiration_date);
    }

    private QrCode(final Amount amountDecimal, final OffsetDateTime expiration_date) {
        this.amount = amountDecimal;
        this.expiration_date = expiration_date;
    }

    public QrCode(
        String id, String text, Amount amount, OffsetDateTime expiration_date,
        List<String> arrangements, List<Link> links)
    {
        this(amount, expiration_date);
        this.id = id;
        this.text = text;
        this.arrangements = Util.getList(arrangements);
        this.links = Util.getList(links);
    }
}
