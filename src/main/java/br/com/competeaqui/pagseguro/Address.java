package br.com.competeaqui.pagseguro;

import lombok.NonNull;

/**
 * @author Manoel Campos da Silva Filho
 * @param locality Bairro
 * @param region_code UF
 * @param country Código do país (com 3 letras)
 * @param postal_code CEP no formato 99999999
 * @see Shipping
 */
public record Address(
        @NonNull String street,
        @NonNull String number,
        @NonNull String complement,
        @NonNull String locality,
        @NonNull String city,
        @NonNull String region_code,
        @NonNull String country,
        @NonNull String postal_code)
{

    public Address(@NonNull String street, @NonNull String number, @NonNull String complement, @NonNull String locality, @NonNull String city, @NonNull String region_code, @NonNull String country, @NonNull String postal_code) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.locality = locality;
        this.city = city;
        this.region_code = region_code;
        this.country = country;
        this.postal_code = Util.getOnlyNumbers(postal_code);
    }

    public static Address empty(){
        return new Address("", "", "", "", "", "", "", "");
    }
}
