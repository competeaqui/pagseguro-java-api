package br.com.competeaqui.pagseguro.service;


import br.com.competeaqui.pagseguro.data.Customer;
import br.com.competeaqui.pagseguro.data.Item;
import br.com.competeaqui.pagseguro.data.Shipping;
import br.com.competeaqui.pagseguro.service.response.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Representa objetos com dados para solicitar a criação de um QRCode de PIX para uma venda (pedido) de um cliente.
 * O {@link PixOrderService} envia um objeto destes com dados iniciais e devolve um outro objeto do mesmo
 * tipo com dados complementares quando o QRCode for gerado com sucesso.
 *
 * @author Manoel Campos da Silva Filho
 * @param id Identificador único do QRCode PIX gerado.
 *           Não deve ser informado quando for feita solicitação de criação de QRCode,
 *           pois é preenchido apenas na resposta da requisição de criação de QRCode PIX.
 *           Tal identificar pode ser usado pela sua aplicação para identificar
 *           qual QRCode foi pago.
 * @param created_at Data/Hora de criação do QRCode.
 *                   Não deve ser informado quando for feita solicitação de criação de QRCode,
 *                   pois é preenchido apenas na resposta da requisição de criação de QRCode PIX.
 * @param shipping Dados de envio do pedido (opcional)
 * @param links Links para o QRCode gerado em diferentes formatos (como PNG e Base64).
 *              Não deve ser informado quando for feita solicitação de criação de QRCode,
 *              pois é preenchido apenas na resposta da requisição de criação de QRCode PIX.
 * @param notification_urls URLs do seu sistema que devem ser receber notificação do PagSeguro
 *                          quando o QRCode for pago.
 * @see Customer
 * @see PixOrderService
 */
public record PixOrder(
        String id,
        @NonNull String reference_id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
        OffsetDateTime created_at,
        @NonNull Customer customer,
        List<Item> items,
        @NonNull List<QrCode> qr_codes,

        Shipping shipping,
        List<Link> links,
        @NonNull List<String> notification_urls)
{
    public PixOrder(@NonNull String reference_id, @NonNull Customer customer) {
        this(null, reference_id, null, customer, new LinkedList<>(), new LinkedList<>(), null, new LinkedList<>(), new LinkedList<>());
    }

    public PixOrder(@NonNull String reference_id, @NonNull Customer customer, QrCode qrcode, @NonNull String notification_url) {
        this(null, reference_id, null, customer, null, List.of(qrcode), null, new LinkedList<>(), List.of(validateUrl(notification_url)));
    }

    private static String validateUrl(final String notification_url) {
        if(notification_url.isBlank())
            throw new IllegalArgumentException("notification_url é obrigatória");

        return notification_url;
    }

}
