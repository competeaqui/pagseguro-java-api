package br.com.competeaqui.pagseguro.data;

import br.com.competeaqui.pagseguro.service.PixOrder;
import lombok.NonNull;

/**
 * Item de uma venda/pedido.
 * @param name Nome (descrição) do item.
 * @param quantity Quantidade do item no pedido.
 * @param unit_amount Valor unitário em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
 *
 * @author Manoel Campos da Silva Filho
 * @see PixOrder
 */
public record Item(@NonNull String name, int quantity, @NonNull int unit_amount, String reference_id) {
}
