package br.com.competeaqui.pagseguro.data;

import br.com.competeaqui.pagseguro.service.PixOrder;
import lombok.NonNull;

/**
 * Informações de entrega de uma venda (pedido) para um cliente
 * @author Manoel Campos da Silva Filho
 * @see PixOrder
 */
public record Shipping(@NonNull Address address) {
    public static Shipping empty(){
        return new Shipping(Address.empty());
    }
}
