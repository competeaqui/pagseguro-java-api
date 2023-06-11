package br.com.competeaqui.pagseguro.service;


import br.com.competeaqui.pagseguro.data.Customer;
import br.com.competeaqui.pagseguro.data.Item;
import br.com.competeaqui.pagseguro.data.Shipping;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Requisição para criar um QRCode de PIX para uma venda (pedido) de um cliente
 * @author Manoel Campos da Silva Filho
 * @see Customer
 * @see PixOrderService
 */
public record PixOrderRequest(
        @NonNull String reference_id,
        @NonNull Customer customer,
        List<Item> items,
        @NonNull List<QrCode> qr_codes,
        Shipping shipping,
        @NonNull List<String> notification_urls)
{
    public PixOrderRequest(@NonNull String reference_id, @NonNull Customer customer) {
        this(reference_id, customer, new LinkedList<>(), new LinkedList<>(), null, new LinkedList<>());
    }

    public PixOrderRequest(@NonNull String reference_id, @NonNull Customer customer, QrCode qrcode, @NonNull String notification_url) {
        this(reference_id, customer, null, List.of(qrcode), null, List.of(validateUrl(notification_url)));
    }

    private static String validateUrl(final String notification_url) {
        if(notification_url.isBlank())
            throw new IllegalArgumentException("notification_url é obrigatória");

        return notification_url;
    }

}
