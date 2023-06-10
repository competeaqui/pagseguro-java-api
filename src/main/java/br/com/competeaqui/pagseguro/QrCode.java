package br.com.competeaqui.pagseguro;

import br.com.competeaqui.pagseguro.service.PixOrderRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * QRCode para pagamento de um pedido/venda.
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
public record QrCode (
        @NonNull Amount amount,
        // 2021-08-29T20:15:59-03:00
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss")
        @NonNull LocalDateTime expiration_date){
}
