package br.com.competeaqui.pagseguro.data;

import br.com.competeaqui.pagseguro.service.PixOrder;
import lombok.NonNull;

/**
 * Item de uma venda/pedido.
 *
 * @param name        Nome (descrição) do item.
 * @param unit_amount Valor unitário em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
 * @param quantity    Quantidade do item no pedido.
 * @author Manoel Campos da Silva Filho
 * @see PixOrder
 */
public record Item(@NonNull String name, int unit_amount, int quantity, String reference_id) {
    /**
     * Cria um item sem um reference_id e com quantidade igual a 1.
     */
    public Item(final String name, final int unit_amount){
        this(name, unit_amount, 1, null);
    }

    /**
     * Cria um item sem um reference_id.
     */
    public Item(final String name, final int unit_amount, final int quantity){
        this(name, unit_amount, quantity, null);
    }

}
