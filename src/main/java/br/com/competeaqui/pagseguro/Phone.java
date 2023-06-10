package br.com.competeaqui.pagseguro;

import lombok.NonNull;

import static br.com.competeaqui.pagseguro.Util.getOnlyNumbers;

/**
 * @param country DDI com 2 dígitos (código do país)
 * @param area DDD com 2 dígitos (código do estado).
 * @param number Número de telefone com 9 dígitos.
 * @param type Tipo do telefone
 * @author Manoel Campos da Silva Filho
 * @see Customer
 */
public record Phone(@NonNull String country, @NonNull String area, @NonNull String number, @NonNull String type) {
    /**
     * Cria um telefone com DDI padrão do Brasil (55).
     * @param area DDD com 2 dígitos (código do estado).
     * @param number Número de telefone com 9 dígitos.
     */
    public Phone(String area, String number, String type) {
        this("55", area, number, type);
    }

    /**
     * Cria um telefone celular com DDI padrão do Brasil (55).
     * @param area DDD com 2 dígitos (código do estado).
     * @param number Número de telefone com 9 dígitos.
     */
    public Phone(final String area, final String number) {
        this(area, number, "MOBILE");
    }

    public Phone(String country, String area, String number, String type) {
        this.country = getOnlyNumbers(country);
        this.area = getOnlyNumbers(area);
        this.number = getOnlyNumbers(number);
        this.type = type;
    }
}
