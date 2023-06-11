import br.com.competeaqui.pagseguro.data.Customer;
import br.com.competeaqui.pagseguro.service.PixOrderRequest;
import br.com.competeaqui.pagseguro.service.PixOrderService;
import br.com.competeaqui.pagseguro.service.QrCode;
import br.com.competeaqui.pagseguro.service.response.PixOrderResponse;
import br.com.competeaqui.pagseguro.service.response.ResponseError;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @see <a href="https://dev.pagseguro.uol.com.br">Documentação da API do PagSeguro</a>
 * @author Manoel Campos da Silva Filho
 */
class AppSample {
    private static final String NOTIFICATION_URL = "https://url-do-seu-sistema-para-receber-confirmacao-pagamento.com";

    /**
     * CPF válido gerado aleatoriamente em https://www.4devs.com.br
     */
    public static final String CPF_CNPJ_CLIENTE = "14880686077";

    public static void main(final String[] args) {
        final var service = new PixOrderService();
        final var customer = new Customer("Manoel", "teste@teste.com", CPF_CNPJ_CLIENTE);
        //TODO: A API só está aceitando valor mínimo de R$ 100 (pode ser somente no sandbox)
        //TODO: Na conversão de BigDecimal pra String, não deve estar aceitando a casa decimal
        final var qrcode = new QrCode(new BigDecimal("100"), OffsetDateTime.now().plusDays(1));
        final var request = new PixOrderRequest("codigo-da-venda", customer, qrcode, NOTIFICATION_URL);
        try {
            final PixOrderResponse response = service.send(request);
            System.out.println(response);
        } catch (final ResponseError e) {
            System.err.println("Erro ao processar requisição");
            e.getError_messages().forEach(System.err::println);
        }
    }
}
