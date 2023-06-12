package br.com.competeaqui.pagseguro.data;

/**
 * Informações sobre o método de pagamento que um cliente usou
 * para pagar um pedido.
 *
 * @param type tipo do método de pagamento (como PIX)
 * @param holder titular do método de pagamento (como dono do cartão ou da conta bancária pela qual foi realizado o pagamento de um PIX)
 * @author Manoel Campos da Silva Filho
 * @see br.com.competeaqui.pagseguro.data.Charge
 */
public record PaymentMethod (String type, Holder holder){
}
