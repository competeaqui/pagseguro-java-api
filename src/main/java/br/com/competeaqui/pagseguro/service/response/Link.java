package br.com.competeaqui.pagseguro.service.response;

import br.com.competeaqui.pagseguro.service.QrCode;
import lombok.NonNull;

/**
 * @author Manoel Campos da Silva Filho
 * @see QrCode
 */
public record Link(@NonNull String rel, @NonNull String href, @NonNull String media, @NonNull String type) {
}
