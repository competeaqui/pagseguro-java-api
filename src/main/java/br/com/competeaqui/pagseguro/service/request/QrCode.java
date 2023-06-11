package br.com.competeaqui.pagseguro.service.request;

import br.com.competeaqui.pagseguro.Amount;
import br.com.competeaqui.pagseguro.service.PixOrderService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Dados para gerar um QRCode para pagamento de um pedido/venda.
 *
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
@Getter @Setter @NoArgsConstructor @ToString
public class QrCode {
    private Amount amount = Amount.zero();

    // Exemplo: 2021-08-29T20:15:59-03:00
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
    private OffsetDateTime expiration_date;

    public QrCode(Amount amount, OffsetDateTime expiration_date) {
        this.amount = Objects.requireNonNullElse(amount, Amount.zero());
        this.expiration_date = expiration_date;
    }
}
