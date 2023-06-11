package br.com.competeaqui.pagseguro.data;

import br.com.competeaqui.pagseguro.service.PixOrderRequest;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Item de uma venda/pedido.
 * @param name Nome (descrição) do item.
 * @param quantity Quantidade do item no pedido.
 * @param unit_amount Valor unitário.
 *
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
public record Item(@NonNull String name, int quantity, @NonNull BigDecimal unit_amount) {
}
