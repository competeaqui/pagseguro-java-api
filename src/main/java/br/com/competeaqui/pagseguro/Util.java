package br.com.competeaqui.pagseguro;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Remove barras duplas de uma URI, exceto :// do protocolo.
     * @param uri URI a ser validada
     * @return a URI trocando // por /
     */
    public static String validateUrl(final String uri) {
        return uri.replaceAll("([^:])(//)", "$1/");
    }

    public static <T> List<T> getList(final List<T> list) {
        return requireNonNullElse(list, new LinkedList<>());
    }
}
