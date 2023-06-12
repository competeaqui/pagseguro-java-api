package br.com.competeaqui.pagseguro.data;

import br.com.competeaqui.pagseguro.service.PixOrderService;
import br.com.competeaqui.pagseguro.service.response.Link;
import br.com.competeaqui.pagseguro.service.response.PaymentResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Informações sobre a confirmação de pagamento de um pedido feito por um cliente.
 *
 * @param status situação do pagamento (como PAID para "PAGO")
 * @author Manoel Campos da Silva Filho
 */
public record Charge (
    String id,
    String reference_id,
    String status,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
    OffsetDateTime created_at,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
    OffsetDateTime paid_at,

    String description,
    Amount amount,
    PaymentResponse payment_response,
    PaymentMethod payment_method,
    List<Link> links){
}