package br.com.competeaqui.pagseguro.service.response;

import br.com.competeaqui.pagseguro.Util;
import br.com.competeaqui.pagseguro.data.Customer;
import br.com.competeaqui.pagseguro.service.PixOrderService;
import br.com.competeaqui.pagseguro.service.QrCode;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Resposta de requisição para criar um QRCode de PIX para uma venda (pedido) de um cliente
 *
 * @author Manoel Campos da Silva Filho
 * @see Customer
 * @see PixOrderService
 */
public record PixOrderResponse(
    String id,
    String reference_id,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PixOrderService.DATE_TIME_FORMAT)
    OffsetDateTime created_at,
    Customer customer,
    List<QrCode> qr_codes,
    List<Link> links,
    List<String> notification_urls){

    public PixOrderResponse(String id, String reference_id, OffsetDateTime created_at, Customer customer, List<QrCode> qr_codes, List<Link> links, List<String> notification_urls) {
        this.id = id;
        this.reference_id = reference_id;
        this.created_at = created_at;
        this.customer = customer;
        this.qr_codes = Util.getList(qr_codes);
        this.links = Util.getList(links);
        this.notification_urls = Util.getList(notification_urls);
    }
}
