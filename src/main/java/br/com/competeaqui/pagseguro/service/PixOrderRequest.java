package br.com.competeaqui.pagseguro.service;


import br.com.competeaqui.pagseguro.Customer;
import br.com.competeaqui.pagseguro.Item;
import br.com.competeaqui.pagseguro.QrCode;
import br.com.competeaqui.pagseguro.Shipping;
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
        @NonNull List<Item> items,
        @NonNull List<QrCode> qr_codes,
        @NonNull Shipping shipping,
        @NonNull List<String> notification_urls)
{
    public PixOrderRequest(@NonNull String reference_id, @NonNull Customer customer) {
        this(reference_id, customer, new LinkedList<>(), new LinkedList<>(), Shipping.empty(), new LinkedList<>());
    }

    public PixOrderRequest(@NonNull String reference_id, @NonNull Customer customer, @NonNull QrCode qrcode, @NonNull String notificationUrl) {
        this(reference_id, customer, new LinkedList<>(), List.of(qrcode), Shipping.empty(), List.of(notificationUrl));
    }

}
