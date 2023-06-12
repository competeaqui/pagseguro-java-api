package br.com.competeaqui.pagseguro.data;

/**
 * Resumo com informações sobre o pagamento de um determinado {@link Amount valor}.
 * @author Manoel Campos da Silva Filho
 * @see Amount
 */
public record Summary (
    int total,
    int paid,
    int refunded){
}