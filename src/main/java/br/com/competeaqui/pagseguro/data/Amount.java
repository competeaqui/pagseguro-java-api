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
public record Amount (@NonNull int value, String currency, Summary summary){

    /**
     * Moeda padrão (caso nenhuma for especificada)
     */
    public static final String DEF_CURRENCY = "BRL";

    /**
     * Cria uma instaância com um determinado valor
     * @param value valor em centavos, sendo o valor mínimo 100 centavos (R$ 1,00)
     */
    public Amount(@NonNull int value) {
        this(value, DEF_CURRENCY, null);
    }

    public static Amount zero(){
        return new Amount(0, DEF_CURRENCY, null);
    }
}
