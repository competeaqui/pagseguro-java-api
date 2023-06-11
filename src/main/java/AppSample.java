import br.com.competeaqui.pagseguro.data.Customer;
import br.com.competeaqui.pagseguro.service.PixOrderRequest;
import br.com.competeaqui.pagseguro.service.PixOrderService;
import br.com.competeaqui.pagseguro.service.QrCode;
import br.com.competeaqui.pagseguro.service.response.Link;
import br.com.competeaqui.pagseguro.service.response.PixOrderResponse;
import br.com.competeaqui.pagseguro.service.response.ResponseError;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

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
            printResponse(response);
        } catch (final ResponseError e) {
            System.err.println("Erro ao processar requisição");
            e.getError_messages().forEach(System.err::println);
        }
    }

    private static void printResponse(final PixOrderResponse response) {
        System.out.printf("QRCode id: %s%n", response.id());
        System.out.printf("reference_id: %s%n", response.reference_id());
        System.out.printf("created_at: %s%n", response.created_at());
        System.out.printf("customer: %s%n", response.customer());
        printLinks(response.links(), "");
        response.notification_urls().forEach(System.out::println);
        response.qr_codes().forEach(AppSample::printQrCode);
    }

    private static void printQrCode(final QrCode code) {
        System.out.println("QRCode:");
        System.out.printf("\tid: %s%n", code.getId());
        System.out.printf("\ttext: %s%n", code.getText());
        System.out.printf("\texpiration_date: %s%n", code.getExpiration_date());
        System.out.printf("\tarrangements: %s%n", code.getArrangements());
        printLinks(code.getLinks(), "\t");
    }

    private static void printLinks(final List<Link> links, final String ident) {
        System.out.printf("%slinks:%n", ident);
        links.forEach(link -> System.out.printf("%s\t%s%n", ident, link));
    }
}
