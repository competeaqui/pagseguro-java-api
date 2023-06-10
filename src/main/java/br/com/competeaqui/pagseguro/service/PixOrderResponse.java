package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.Customer;

/**
 * Resposta de requisição para criar um QRCode de PIX para uma venda (pedido) de um cliente
 * @author Manoel Campos da Silva Filho
 * @see Customer
 * @see PixOrderService
 */
public record PixOrderResponse (
        String id
){
}
