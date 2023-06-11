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

    public static String insertTraillingSlash(String path) {
        path = requireNonNullElse(path, "");
        return path.endsWith("/") ? path : path + "/";
    }

    public static <T> List<T> getList(final List<T> list) {
        return requireNonNullElse(list, new LinkedList<>());
    }
}
