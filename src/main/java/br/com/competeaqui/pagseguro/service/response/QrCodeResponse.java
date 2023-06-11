package br.com.competeaqui.pagseguro.service.response;

import br.com.competeaqui.pagseguro.Amount;
import br.com.competeaqui.pagseguro.Util;
import br.com.competeaqui.pagseguro.service.request.PixOrderRequest;
import br.com.competeaqui.pagseguro.service.request.QrCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Objeto de resposta após ser concluída a geração do QRCode
 *
 * @author Manoel Campos da Silva Filho
 * @see PixOrderRequest
 */
@Getter @Setter @NoArgsConstructor
public class QrCodeResponse extends QrCode {
    private String id;
    private String text;
    private List<String> arrangements;
    private List<Link> links;

    public QrCodeResponse(
            String id, String text, Amount amount, OffsetDateTime expiration_date,
            List<String> arrangements, List<Link> links)
    {
        super(amount, expiration_date);
        this.id = id;
        this.text = text;
        this.arrangements = Util.getList(arrangements);
        this.links = Util.getList(links);
    }
}
