package br.com.competeaqui.pagseguro;

import br.com.competeaqui.pagseguro.service.PixOrderRequest;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Cliente para quem está sendo feito uma venda.
 * @author Manoel Campos da Silva Filho
 * @param tax_id CPF do cliente.
 * @see PixOrderRequest
 */
public record Customer(@NonNull String name, @NonNull String email, @NonNull String tax_id, @NonNull List<Phone> phones) {
    public Customer(String name, String email, String tax_id) {
        this(name, email, tax_id, new LinkedList<>());
    }
}
