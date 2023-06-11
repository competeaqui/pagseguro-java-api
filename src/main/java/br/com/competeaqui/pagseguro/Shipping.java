package br.com.competeaqui.pagseguro;

import br.com.competeaqui.pagseguro.service.request.PixOrderRequest;
import lombok.NonNull;

/**
 * Informações de entrega de uma venda (pedido) para um cliente
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
public record Shipping(@NonNull Address address) {
    public static Shipping empty(){
        return new Shipping(Address.empty());
    }
}
