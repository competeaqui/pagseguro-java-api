package br.com.competeaqui.pagseguro.data;

import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Valor monetário para uma transação.
 * @param currency Código da Moeda (com 3 letras)
 * @param summary Resumo com informações sobre o pagamento
 *                (preenchido somente quando o PagSeguro envia notificação
 *                para indicar que o pagamento de um pedido foi feito).
 *                Assim, em requisições para solicitar um pagamento,
 *                tal atributo não deve ser preenchido.
 * @author Manoel Campos da Silva Filho
 */
public record Amount (@NonNull BigDecimal value, String currency, Summary summary){
    public Amount(@NonNull String value) {
        this(new BigDecimal(value));
    }

    public Amount(@NonNull BigDecimal value) {
        this(value, "BRL", null);
    }

    public static Amount zero(){
        return new Amount(BigDecimal.ZERO, "BRL", null);
    }
}
