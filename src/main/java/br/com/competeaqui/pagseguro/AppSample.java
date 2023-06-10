package br.com.competeaqui.pagseguro;

import br.com.competeaqui.pagseguro.service.PixOrderRequest;
import br.com.competeaqui.pagseguro.service.PixOrderService;

import java.time.LocalDateTime;

/**
 * @see <a href="https://dev.pagseguro.uol.com.br">Documentação da API do PagSeguro</a>
 * @author Manoel Campos da Silva Filho
 */
class AppSample {
    private static final String NOTIFICATION_URL = "";

    public static void main(final String[] args) {
        final var service = new PixOrderService();
        final var customer = new Customer("Manoel", "teste@teste.com", "11111111111");
        final var qrcode = new QrCode(new Amount("10.0"), LocalDateTime.now().plusDays(1));
        final var request = new PixOrderRequest("codigo-da-venda", customer, qrcode, NOTIFICATION_URL);
        System.out.println(service.send(request));
    }
}
