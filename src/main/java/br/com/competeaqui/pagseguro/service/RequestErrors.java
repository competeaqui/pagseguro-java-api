package br.com.competeaqui.pagseguro.service;

import br.com.competeaqui.pagseguro.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Manoel Campos da Silva Filho
 * @see Customer
 * @see PixOrderService
 */
@Getter @Setter
public class RequestErrors extends RuntimeException {
    private List<ErrorMessage> error_messages = new LinkedList<>();
}
