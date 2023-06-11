package br.com.competeaqui.pagseguro.service.response;

import br.com.competeaqui.pagseguro.Customer;
import br.com.competeaqui.pagseguro.service.PixOrderService;
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
public class ResponseError extends RuntimeException {
    private List<ErrorMessage> error_messages = new LinkedList<>();
}
