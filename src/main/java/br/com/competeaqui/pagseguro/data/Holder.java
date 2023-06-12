package br.com.competeaqui.pagseguro.data;

/**
 * Informações sobre o titular de um método de pagamento
 * utilizado para pagar um determinado pedido.
 *
 * @author Manoel Campos da Silva Filho
 * @see br.com.competeaqui.pagseguro.data.Charge
 */
public record Holder(String name, String tax_id) {
}
