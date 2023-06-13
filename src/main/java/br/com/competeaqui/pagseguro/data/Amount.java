package br.com.competeaqui.pagseguro.data;

import lombok.NonNull;

/**
 * Valor monetário para uma transação.
 * @param value valor em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
 * @param currency Código da Moeda (com 3 letras)
 * @param summary Resumo com informações sobre o pagamento
 *                (preenchido somente quando o PagSeguro envia notificação
 *                para indicar que o pagamento de um pedido foi feito).
 *                Assim, em requisições para solicitar um pagamento,
 *                tal atributo não deve ser preenchido.
 * @author Manoel Campos da Silva Filho
 */
public record Amount (@NonNull long value, String currency, Summary summary){
    /**
     * Cria uma instaância com um determinado valor
     * @param value valor em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
     */
    public Amount(@NonNull long value) {
        this(value, "BRL", null);
    }

    public static Amount zero(){
        return new Amount(0L, "BRL", null);
    }
}
