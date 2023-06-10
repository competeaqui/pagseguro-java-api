package br.com.competeaqui.pagseguro;


import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Valor monetário para uma transação.
 * @author Manoel Campos da Silva Filho
 */
public record Amount (@NonNull BigDecimal value){
    public Amount(@NonNull String value) {
        this(new BigDecimal(value));
    }
}
