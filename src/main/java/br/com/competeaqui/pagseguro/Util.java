package br.com.competeaqui.pagseguro;

import static java.util.Objects.requireNonNullElse;

public class Util {
    /**
     * Retorna a String passada removendo qualquer caractere que não seja um número.
     * @param str String para remover caracteres não numéricos
     * @return String somente com números
     */
    public static String getOnlyNumbers(final String str) {
        return requireNonNullElse(str, "").replaceAll("\\D", "");
    }
}
